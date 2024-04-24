use SuperPenguin;


Delimiter $$
create function fn_CalcularPromocion(prodId int) returns decimal (10,2) deterministic
begin
	declare resultado int default 0;
    declare fechaFin date;
    declare i int default 1;

    resultadoLoop : loop
    

    if fechaFinalizacion = (select promocionId from promociones PR where promocionId = i) then
		set fechaFin = (select PR.fechaFinalizacion from Promociones PR where productoId = (select productoId from Promociones where promocionId = i));
        
		 if fechaFin>date(now()) then
			set resultado = 0;
		else 
			set resultado = 1;
         end if;
    end if;
    
    if i = (select count(*) from Promociones) then
		leave resultadoLoop;
    end if;
    
    set i = i +1;
    end loop resultadoLoop;
    
    return resultado;
end $$
Delimiter ;


Delimiter $$
create function fn_totalFactura(factId int) returns decimal(10,2) deterministic
begin
    declare total decimal(10,2) default 0.0;
    declare i int default 1;
    declare precio decimal(10,2);

    totalLoop: loop
        if fn_CalcularPromocion(factId) = 0 then
            if factId = (select facturaId from detalleFactura DF where detalleFacturaId = i) then
                set precio = (select P.precioVentaUnitario from Productos P where productoId = (select productoId from detalleFactura where detalleFacturaId = i));
                set total = total + precio + (precio*0.12);
            end if;
        else 
            if factId = (select facturaId from detalleFactura DF where detalleFacturaId = i) then
                set precio = (select PR.precioPromocion from Prmociones PR where productoId = (select productoId from detalleFactura where detalleFacturaId = i));
                set total = total + precio + (precio*0.12);
            end if;
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
    
    totalLoop : loop
    
    if compId = (select DC.compraId from detalleCompra DC where detalleCompraId = i) then
		set precio = (select P.precioCompra from Productos P where productoId = (select productoId from detalleFactura where detalleCompraId = i));
        set cantidadComprada = (select DC.cantidadCompra from detalleCompra DC where productoId = (select productoId from detalleCompra where detalleCompraId = i));
        
        set totalC = precio * cantidadComprada + (cantidadComprada*precio*1.12);
    end if;
    
    if i = (select count(*) from detalleFactura) then
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
	
    select cantidadStock into cantidad from productos where productoId = productId;
    select cantidadCompra into cantidadComprada from detalleCompra where productoId = productId;
    
    set stockActual = cantidadComprada + cantidad;
    
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