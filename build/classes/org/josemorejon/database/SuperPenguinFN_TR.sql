use SuperPenguin;


DELIMITER $$
CREATE FUNCTION fn_CalcularPromocion(prodId INT) RETURNS INT DETERMINISTIC
BEGIN
    DECLARE resultado INT DEFAULT 0;
    DECLARE fechaFin DATE;

    SELECT fechaFinalizacion INTO fechaFin
		FROM Promociones
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


Delimiter $$
create function fn_totalFactura(factId int) returns decimal(10,2) deterministic
begin
    declare total decimal(10,2) default 0.0;
    declare precio decimal(10,2);
    declare i int default 1;
    declare curFacturaId, curProductoId int;
    declare cursorDetalleFactura cursor for 
		select DF.facturaId, DF.productoId from DetalleFactura DF
	;
    open cursorDetalleFactura;
    totalLoop : loop
    fetch cursorDetalleFactura into curFacturaId, curProductoId;
    if factId = curFacturaId then
		if(fn_CalcularPromocion(curProductoId) = 0) then
			set precio = (select P.precioVentaUnitario from Productos P where P.productoId = curProductoId);
		else 
			set precio = (select P.precioPromocion from Promociones P where P.promocionId = fn_CalcularPromocion(curProductoId));
        end if;
        set total = total + (precio * 1.12);
    end if;
    if i = (select count(*) from detalleFactura) then
		leave totalLoop;
    end if;
    set i = i + 1;
    end loop totalLoop;
    
    call sp_asignarTotalFactura(factId,total);
    return total;
end $$
Delimiter ;





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