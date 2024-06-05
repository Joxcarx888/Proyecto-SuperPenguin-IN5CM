use SuperPenguin;


DELIMITER $$
CREATE FUNCTION fn_CalcularPromocion(prodId INT) RETURNS INT DETERMINISTIC
BEGIN
    DECLARE resultado INT DEFAULT 0;
    DECLARE fechaFin DATE;

    SELECT fechaFinalizacion INTO fechaFin FROM Promociones
		WHERE productoId = prodId
			ORDER BY fechaFinalizacion DESC LIMIT 1;

    IF fechaFin IS NOT NULL AND fechaFin > DATE(NOW()) THEN
        SET resultado = 1;
    ELSE
        SET resultado = 0;
    END IF;

    RETURN resultado;
END$$
DELIMITER ;


DELIMITER $$
CREATE FUNCTION fn_totalFactura(factId INT) RETURNS DECIMAL(10,2) DETERMINISTIC
BEGIN
    DECLARE totalF DECIMAL(10,2) DEFAULT 0.0;
    DECLARE subtotalF DECIMAL(10,2) DEFAULT 0.0;
    DECLARE precio DECIMAL(10,2);
    DECLARE curProductoId INT;
    DECLARE cursorDetalleFactura CURSOR FOR 
        SELECT DF.productoId  FROM DetalleFactura DF
			WHERE DF.facturaId = factId;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET curProductoId = NULL;

    SELECT total INTO totalF FROM Facturas
		WHERE facturaId = factId;

    IF totalF IS NULL THEN
        SET totalF = 0.0;
    END IF;

    OPEN cursorDetalleFactura;
    totalLoop: LOOP
        FETCH cursorDetalleFactura INTO curProductoId;
        IF curProductoId IS NULL THEN
            LEAVE totalLoop;
        END IF;

        IF fn_CalcularPromocion(curProductoId) = 1 THEN
            SELECT P.precioPromocion INTO precio FROM Promociones P
				WHERE P.productoId = curProductoId
				ORDER BY P.fechaFinalizacion DESC LIMIT 1;
        ELSE
            SELECT P.precioVentaUnitario INTO precio FROM Productos P
				WHERE P.productoId = curProductoId;
        END IF;
        
        SET subtotalF = subtotalF + precio;
    END LOOP totalLoop;
    CLOSE cursorDetalleFactura;

    SET totalF = subtotalF * 1.12;

    CALL sp_asignarTotalFactura(factId, totalF);

    RETURN totalF;
END$$
DELIMITER ;





Delimiter $$
create function fn_eliminarStock(productId int) returns int deterministic
begin
    declare stockActual int default 0;
    declare cantidadComprada int default 0;

    select cantidadStock into cantidadComprada from Productos where productoId = productId;
    
    set stockActual = cantidadComprada - 1;
    
    call sp_modificarStock(productId, stockActual);
    
    return stockActual;
end $$
Delimiter ;

Delimiter $$
create trigger tg_totalFactura
after insert on DetalleFactura
for each row
Begin
    declare totalFact decimal(10,2);
    declare stockActual int;
    
    set totalFact = fn_totalFactura(new.facturaId);
    set stockActual = fn_eliminarStock(new.productoId); 
End$$
Delimiter ;









Delimiter $$
create function fn_totalCompra(compId int) returns decimal (10,2) deterministic
begin
	declare totalC decimal (10,2) default 0.0;
    declare i int default 1;
    declare precio decimal (10,2);
    declare cantidadComprada int default 0;
    declare curCantidadCompra, curProductoId, curCompraId int;
    
    declare cursorDetalleCompra cursor for
		select DC.cantidadCompra, DC.productoId, DC.compraId from DetalleCompra DC
	;
    
    open cursorDetalleCompra;
    
    totalLoop : loop
    fetch cursorDetalleCompra into curCantidadCompra, curProductoId, curCompraId;
    
    if compId = curCompraId then
		set precio = (select P.precioCompra from Productos P where P.productoId = curProductoId);
		set cantidadComprada = curCantidadCompra;
		set totalC = totalC + (precio * cantidadComprada + (cantidadComprada*precio*0.12));
    end if;
    
    if i = (select count(*) from detalleCompra) then
		leave totalLoop;
    end if;
    
    set i = i +1;
    end loop totalLoop;
    
    call sp_asignarTotalCompra(compId,totalC);
    
    return totalC;
end $$
Delimiter ;


Delimiter $$
create function fn_aumentarStock(productId int) returns int deterministic
begin
    declare stockActual int default 0;
    declare cantidadComprada int default 0;
    declare cantidad int default 0;
	
    select cantidadStock into cantidad from productos where productoId = productId LIMIT 1;
    select cantidadCompra into cantidadComprada from detalleCompra where productoId = productId LIMIT 1;
    
    set stockActual = stockActual + cantidadComprada + cantidad;
    
    call sp_modificarStockCompra(productId, stockActual);
    
    return stockActual;
end $$
Delimiter ;


Delimiter $$
create trigger tg_totalCompra
after insert on DetalleCompra
for each row
Begin
    declare totalC decimal(10,2);
    declare stockActual int;
    
    set totalC= fn_totalCompra(new.compraId);
    set stockActual = fn_aumentarStock(new.productoId); 
End$$
Delimiter ;