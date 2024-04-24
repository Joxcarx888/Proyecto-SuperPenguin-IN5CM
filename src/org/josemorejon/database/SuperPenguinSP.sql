use SuperPenguin;

-- CRUD CLIENTES

DELIMITER $$
CREATE PROCEDURE sp_agregarCliente(nom VARCHAR(30), ape VARCHAR(30), tel VARCHAR(15), dir VARCHAR(200), ni VARCHAR(15))
BEGIN
    INSERT INTO Clientes(nombre, apellido, telefono, direccion, nit)
    VALUES (nom, ape, tel, dir, ni);
END$$
DELIMITER ;


CALL sp_agregarCliente('Lewis', 'Hamilton', '9999-6666', 'Ciudad Quetzal', '1234567890123');

DELIMITER $$
CREATE PROCEDURE sp_ListarClientes()
BEGIN
    SELECT clienteId, 
		nombre, 
		apellido, 
		telefono, 
		direccion, 
		nit 
		FROM Clientes;
END$$
DELIMITER ;


CALL sp_ListarClientes();


DELIMITER $$
CREATE PROCEDURE sp_EliminarCliente(IN clidId INT)
BEGIN
    DELETE FROM Clientes 
    WHERE clienteId = clidId;
END$$
DELIMITER ;


CALL sp_EliminarCliente(7);


DELIMITER $$
CREATE PROCEDURE sp_BuscarCliente(IN clidId INT)
BEGIN
    SELECT clienteId, 
			nombre, 
			apellido, 
			telefono, 
			direccion, 
			nit 
			FROM Clientes WHERE clienteId = clidId;
END$$
DELIMITER ;


CALL sp_BuscarCliente(3);


DELIMITER $$
CREATE PROCEDURE sp_EditarCliente(IN clidId INT, IN nom VARCHAR(30), IN ape VARCHAR(30), IN tel VARCHAR(15), IN dir VARCHAR(200), IN ni VARCHAR(15))
BEGIN
    UPDATE Clientes
    SET nombre = nom, apellido = ape, telefono = tel, direccion = dir, nit = ni
    WHERE clienteId = clidId;
END$$
DELIMITER ;


CALL sp_EditarCliente(2, 'Diego', 'De Leon', '2222-3333', 'Ciudad Reforma', '9876543210987');

-- CRUD CARGOS*************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarCargos(nomC VARCHAR(30), descripC VARCHAR(100))
BEGIN
    INSERT INTO Cargos(nombreCargo, descripcionCargo)
    VALUES (nomC, descripC);
END$$
DELIMITER ;


CALL sp_agregarCargos('Cajero', 'Cobra a los Clientes' );

DELIMITER $$
CREATE PROCEDURE sp_ListarCargos()
BEGIN
    SELECT cargoId, 
		nombreCargo, 
		descripcionCargo
		FROM Cargos;
END$$
DELIMITER ;


CALL sp_ListarCargos();

DELIMITER $$
CREATE PROCEDURE sp_EliminarCargo(IN carId INT)
BEGIN
    DELETE FROM Cargos 
    WHERE cargoId = carId;
END$$
DELIMITER ;


CALL sp_EliminarCargo(2);

DELIMITER $$
CREATE PROCEDURE sp_BuscarCargo(IN carId INT)
BEGIN
    SELECT cargoId, 
		nombreCargo, 
		descripcionCargo
		FROM Cargos WHERE cargoId = carId;
END$$
DELIMITER ;


CALL sp_BuscarCargo(2);

DELIMITER $$
CREATE PROCEDURE sp_EditarCargo(IN carId INT, IN nomC VARCHAR(30), IN descripC VARCHAR(100))
BEGIN
    UPDATE Cargos
    SET nombreCargo = nomC, descripcionCargo = descripC
    WHERE cargoId = carId;
END$$
DELIMITER ;


CALL sp_EditarCargo(2, 'Call Center', 'Recibir Llamadas');

-- CRUD EMPLEADOS*************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarEmpleado(nomE VARCHAR(30), apeE VARCHAR(30), sueldoE DECIMAL(10,2), horaEntradaE TIME, horaSalidaE TIME, cargoIdE INT, encargadoIdE INT)
BEGIN
    INSERT INTO Empleados(nombreEmpleado, apellidoEmpleado, sueldo, horaentrada, horaSalida, cargoId, encargadoId)
    VALUES (nomE, apeE, sueldoE, horaEntradaE, horaSalidaE, cargoIdE, encargadoIdE);
END$$
DELIMITER ;


CALL sp_agregarEmpleado('Juan', 'Pérez', 2000.00, '08:00:00', '17:00:00', 1, NULL);

DELIMITER $$
CREATE PROCEDURE sp_ListarEmpleados()
BEGIN
    SELECT empleadoId, 
		nombreEmpleado, 
		apellidoEmpleado,
		sueldo,
		horaentrada,
		horaSalida,
		cargoId,
		encargadoId
	FROM Empleados;
END$$
DELIMITER ;

CALL sp_ListarEmpleados();


DELIMITER $$
CREATE PROCEDURE sp_EliminarEmpleado(IN empId INT)
BEGIN
    DELETE FROM Empleados 
    WHERE empleadoId = empId;
END$$
DELIMITER ;

CALL sp_EliminarEmpleado(2);


DELIMITER $$
CREATE PROCEDURE sp_BuscarEmpleado(IN empId INT)
BEGIN
    SELECT empleadoId, 
		nombreEmpleado, 
		apellidoEmpleado,
		sueldo,
		horaentrada,
		horaSalida,
		cargoId,
		encargadoId
	FROM Empleados 
	WHERE empleadoId = empId;
END$$
DELIMITER ;

CALL sp_BuscarEmpleado(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarEmpleado(IN empId INT, IN nomE VARCHAR(30), IN apeE VARCHAR(30), IN sueldoE DECIMAL(10,2), IN horaEntradaE TIME, IN horaSalidaE TIME, IN cargoIdE INT, IN encargadoIdE INT)
BEGIN
    UPDATE Empleados
    SET nombreEmpleado = nomE, apellidoEmpleado = apeE, sueldo = sueldoE, horaentrada = horaEntradaE, horaSalida = horaSalidaE, cargoId = cargoIdE, encargadoId = encargadoIdE
    WHERE empleadoId = empId;
END$$
DELIMITER ;

CALL sp_EditarEmpleado(2, 'Pedro', 'López', 2500.00, '09:00:00', '18:00:00', 1, NULL);

-- ENCARGADO EMPLEADOS
Delimiter $$
create procedure sp_AsignarEncargado(In empId Int, In encarId int)
begin

	Update Empleados  
		Set 
			Empleados.encargadoId = encarId
			Where empleadoId = empId;
end$$
Delimiter ;

call sp_AsignarEncargado(1,1);

-- CRUD DISTRIBUIDORES*************************************************************************
DELIMITER $$
CREATE PROCEDURE sp_agregarDistribuidor(nomD VARCHAR(30), dirD VARCHAR(200), niD VARCHAR(15), telD VARCHAR(15), webD VARCHAR(50))
BEGIN
    INSERT INTO Distribuidores(nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web)
    VALUES (nomD, dirD, niD, telD, webD);
END$$
DELIMITER ;


CALL sp_agregarDistribuidor('Iron Maiden', 'Reino Unido', '1234567890123', '666-666', 'www.IronMaiden.com');

DELIMITER $$
CREATE PROCEDURE sp_ListarDistribuidores()
BEGIN
    SELECT distribuidorId, 
		nombreDistribuidor, 
		direccionDistribuidor,
		nitDistribuidor,
		telefonoDistribuidor,
		web
	FROM Distribuidores;
END$$
DELIMITER ;

CALL sp_ListarDistribuidores();


DELIMITER $$
CREATE PROCEDURE sp_EliminarDistribuidor(IN distId INT)
BEGIN
    DELETE FROM Distribuidores 
    WHERE distribuidorId = distId;
END$$
DELIMITER ;

CALL sp_EliminarDistribuidor(2);


DELIMITER $$
CREATE PROCEDURE sp_BuscarDistribuidor(IN distId INT)
BEGIN
    SELECT distribuidorId, 
		nombreDistribuidor, 
		direccionDistribuidor,
		nitDistribuidor,
		telefonoDistribuidor,
		web
	FROM Distribuidores 
	WHERE distribuidorId = distId;
END$$
DELIMITER ;

CALL sp_BuscarDistribuidor(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarDistribuidor(IN distId INT, IN nomD VARCHAR(30), IN dirD VARCHAR(200), IN niD VARCHAR(15), IN telD VARCHAR(15), IN webD VARCHAR(50))
BEGIN
    UPDATE Distribuidores
    SET nombreDistribuidor = nomD, direccionDistribuidor = dirD, nitDistribuidor = niD, telefonoDistribuidor = telD, web = webD
    WHERE distribuidorId = distId;
END$$
DELIMITER ;

CALL sp_EditarDistribuidor(1, 'Real Madrid', 'Ciudad Deportiva Madrid,Madrid,España', '1111111111111', '777-7777', 'www.RealMadrid.com');

-- CRUD CATEGORIA PRODUCTO*************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarCategoriaProducto(nomCP VARCHAR(30), descCP VARCHAR(100))
BEGIN
    INSERT INTO CategoriaProductos(nombreCategoria, descripcionCategoria)
    VALUES (nomCP, descCP);
END$$
DELIMITER ;

CALL sp_agregarCategoriaProducto('Electrónicos', 'Productos electrónicos de consumo');

DELIMITER $$
CREATE PROCEDURE sp_ListarCategoriasProductos()
BEGIN
    SELECT categoriaproductosId, 
		nombreCategoria, 
		descripcionCategoria
	FROM CategoriaProductos;
END$$
DELIMITER ;

CALL sp_ListarCategoriasProductos();


DELIMITER $$
CREATE PROCEDURE sp_EliminarCategoriaProducto(IN catProdId INT)
BEGIN
    DELETE FROM CategoriaProductos 
    WHERE categoriaproductosId = catProdId;
END$$
DELIMITER ;

CALL sp_EliminarCategoriaProducto(2);


DELIMITER $$
CREATE PROCEDURE sp_BuscarCategoriaProducto(IN catProdId INT)
BEGIN
    SELECT categoriaproductosId, 
		nombreCategoria, 
		descripcionCategoria
	FROM CategoriaProductos 
	WHERE categoriaproductosId = catProdId;
END$$
DELIMITER ;

CALL sp_BuscarCategoriaProducto(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarCategoriaProducto(IN catProdId INT, IN nomCP VARCHAR(30), IN descCP VARCHAR(100))
BEGIN
    UPDATE CategoriaProductos
    SET nombreCategoria = nomCP, descripcionCategoria = descCP
    WHERE categoriaproductosId = catProdId;
END$$
DELIMITER ;

CALL sp_EditarCategoriaProducto(1, 'Ropa', 'Ropa y accesorios para todas las edades');

-- CRUD COMPRA *************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarCompra(fechaComp DATE)
BEGIN
    INSERT INTO Compras(fechaCompra)
    VALUES (fechaComp);
END$$
DELIMITER ;

CALL sp_agregarCompra('2024-04-23');

DELIMITER $$
CREATE PROCEDURE sp_ListarCompras()
BEGIN
    SELECT compraId, 
		fechaCompra, 
		totalCompra
	FROM Compras;
END$$
DELIMITER ;

CALL sp_ListarCompras();


DELIMITER $$
CREATE PROCEDURE sp_EliminarCompra(IN compId INT)
BEGIN
    DELETE FROM Compras 
    WHERE compraId = compId;
END$$
DELIMITER ;

CALL sp_EliminarCompra(2);


DELIMITER $$
CREATE PROCEDURE sp_BuscarCompra(IN compId INT)
BEGIN
    SELECT compraId, 
		fechaCompra, 
		totalCompra
	FROM Compras 
	WHERE compraId = compId;
END$$
DELIMITER ;

CALL sp_BuscarCompra(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarCompra(IN compId INT, IN fechaComp DATE)
BEGIN
    UPDATE Compras
    SET fechaCompra = fechaComp
    WHERE compraId = compId;
END$$
DELIMITER ;

CALL sp_EditarCompra(2, '2024-04-24');

-- CRUD FACTURAS *************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarFactura(fechaFact DATE, horaFact TIME, cliId INT, empId INT)
BEGIN
    INSERT INTO Facturas(fecha, hora, clienteId, empleadoId)
    VALUES (fechaFact, horaFact, cliId, empId);
END$$
DELIMITER ;

CALL sp_agregarFactura('2024-04-23', '08:30:00', 1, 1);

DELIMITER $$
CREATE PROCEDURE sp_ListarFacturas()
BEGIN
    SELECT facturaId, 
		fecha, 
		hora,
		clienteId,
		empleadoId,
		total
	FROM Facturas;
END$$
DELIMITER ;

CALL sp_ListarFacturas();


DELIMITER $$
CREATE PROCEDURE sp_EliminarFactura(IN factId INT)
BEGIN
    DELETE FROM Facturas 
    WHERE facturaId = factId;
END$$
DELIMITER ;

CALL sp_EliminarFactura(2);


DELIMITER $$
CREATE PROCEDURE sp_BuscarFactura(IN factId INT)
BEGIN
    SELECT facturaId, 
		fecha, 
		hora,
		clienteId,
		empleadoId,
		total
	FROM Facturas 
	WHERE facturaId = factId;
END$$
DELIMITER ;

CALL sp_BuscarFactura(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarFactura(IN factId INT, IN fechaFact DATE, IN horaFact TIME, IN cliId INT, IN empId INT)
BEGIN
    UPDATE Facturas
    SET fecha = fechaFact, hora = horaFact, clienteId = cliId, empleadoId = empId
    WHERE facturaId = factId;
END$$
DELIMITER ;

CALL sp_EditarFactura(2, '2024-04-24', '09:00:00', 1, 1);


-- CRUD TicketSoporte *************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarTicketSoporte(descTicket VARCHAR(250), clienteIdTicket INT, facturaIdTicket INT)
BEGIN
    INSERT INTO TicketSoporte(descripcionTicket, estatus, clienteId, facturaId)
    VALUES (descTicket, 'Recién creado', clienteIdTicket, facturaIdTicket);
END$$
DELIMITER ;


CALL sp_agregarTicketSoporte('Problema con el correo electrónico', 1, Null);

DELIMITER $$
CREATE PROCEDURE sp_ListarTicketsSoporte()
BEGIN
    SELECT ticketSoporteId, 
		descripcionTicket, 
		estatus,
		clienteId,
		facturaId
	FROM TicketSoporte;
END$$
DELIMITER ;

CALL sp_ListarTicketsSoporte();


DELIMITER $$
CREATE PROCEDURE sp_EliminarTicketSoporte(IN ticketId INT)
BEGIN
    DELETE FROM TicketSoporte 
    WHERE ticketSoporteId = ticketId;
END$$
DELIMITER ;

CALL sp_EliminarTicketSoporte(1);


DELIMITER $$
CREATE PROCEDURE sp_BuscarTicketSoporte(IN ticketId INT)
BEGIN
    SELECT ticketSoporteId, 
		descripcionTicket, 
		estatus,
		clienteId,
		facturaId
	FROM TicketSoporte 
	WHERE ticketSoporteId = ticketId;
END$$
DELIMITER ;

CALL sp_BuscarTicketSoporte(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarTicketSoporte(IN ticketId INT, IN descTicket VARCHAR(250), IN estatusTicket VARCHAR(30), IN clienteIdTicket INT, IN facturaIdTicket INT)
BEGIN
    UPDATE TicketSoporte
    SET descripcionTicket = descTicket, estatus = estatusTicket, clienteId = clienteIdTicket, facturaId = facturaIdTicket
    WHERE ticketSoporteId = ticketId;
END$$
DELIMITER ;

CALL sp_EditarTicketSoporte(2, 'Los narcos se van a morir', 'Resuelto', 1, NULL);


-- CRUD PRODUCTOS *************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarProductos(nomP VARCHAR(50), descP VARCHAR(100), cantStock INT, precioVenta DECIMAL(10,2), precioVentaM DECIMAL(10,2), precioComp DECIMAL(10,2), imgP BLOB, distId INT, cateId INT)
BEGIN
	INSERT INTO Productos(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaproductosId)
	VALUES (nomP, descP, cantStock, precioVenta, precioVentaM, precioComp, imgP, distId, cateId);
END$$
DELIMITER ;

CALL sp_agregarProductos('Laptop HP', 'I5 8 RAM 500GBssd', 100, 3000, 2500, 1800, NULL, 1, 1);

DELIMITER $$
CREATE PROCEDURE sp_ListarProductos()
BEGIN
	SELECT productoId, 
		nombreProducto, 
        descripcionProducto, 
        cantidadStock, 
        precioVentaUnitario, 
        precioVentaMayor, 
        precioCompra, 
        distribuidorId, 
        categoriaproductosId 
	FROM Productos;
END$$
DELIMITER ;

CALL sp_ListarProductos();

DELIMITER $$
CREATE PROCEDURE sp_EliminarProducto(IN prodId INT)
BEGIN
	DELETE FROM Productos 
		WHERE productoId = prodId;
END$$
DELIMITER ;

CALL sp_EliminarProducto(2);

DELIMITER $$
CREATE PROCEDURE sp_BuscarProducto(IN prodId INT)
BEGIN
	SELECT productoId, 
		nombreProducto, 
        descripcionProducto, 
		cantidadStock, 
		precioVentaUnitario, 
        precioVentaMayor, 
        precioCompra, 
        distribuidorId, 
        categoriaproductosId 
	FROM Productos 
		WHERE productoId = prodId;
END$$
DELIMITER ;

CALL sp_BuscarProducto(1);

DELIMITER $$
CREATE PROCEDURE sp_EditarProducto(IN prodId INT, IN nomP VARCHAR(50), IN descP VARCHAR(100), IN cantStock INT, IN precioVenta DECIMAL(10,2), IN precioVentaM DECIMAL(10,2), IN precioComp DECIMAL(10,2), IN imgP BLOB, IN distId INT, IN catId INT)
BEGIN
	UPDATE Productos 
	SET nombreProducto = nomP, descripcionProducto = descP, cantidadStock = cantStock, precioVentaUnitario = precioVenta, precioVentaMayor = precioVentaM, precioCompra = precioComp, imagenProducto = imgP, distribuidorId = distId, categoriaproductosId = catId 
    WHERE productoId = prodId;
END$$
DELIMITER ;

CALL sp_EditarProducto(1, 'Moto Edge 50 Pro', '512GB 12 RAM 50Mpx Camara', 150, 4500.00, 3800.00, 2500.00, NULL, 1,1 );

-- CRUD DETALLE COMPRA *************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarDetalleCompra(cantComp INT, prodIdComp INT, compId INT)
BEGIN
    INSERT INTO detalleCompra(cantidadCompra, productoId, compraId)
    VALUES (cantComp, prodIdComp, compId);
END$$
DELIMITER ;

CALL sp_agregarDetalleCompra(5, 1, 1);

DELIMITER $$
CREATE PROCEDURE sp_ListarDetalleCompra()
BEGIN
    SELECT detalleCompraId, 
		cantidadCompra, 
		productoId, 
		compraId
	FROM detalleCompra;
END$$
DELIMITER ;

CALL sp_ListarDetalleCompra();


DELIMITER $$
CREATE PROCEDURE sp_EliminarDetalleCompra(IN detCompId INT)
BEGIN
    DELETE FROM detalleCompra 
    WHERE detalleCompraId = detCompId;
END$$
DELIMITER ;

CALL sp_EliminarDetalleCompra(1);


DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleCompra(IN detCompId INT)
BEGIN
    SELECT detalleCompraId, 
		cantidadCompra, 
		productoId, 
		compraId
	FROM detalleCompra 
	WHERE detalleCompraId = detCompId;
END$$
DELIMITER ;

CALL sp_BuscarDetalleCompra(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarDetalleCompra(IN detCompId INT, IN cantComp INT, IN prodIdComp INT, IN compId INT)
BEGIN
    UPDATE detalleCompra
    SET cantidadCompra = cantComp, productoId = prodIdComp, compraId = compId
    WHERE detalleCompraId = detCompId;
END$$
DELIMITER ;

CALL sp_EditarDetalleCompra(1, 10, 1, 1);



-- CRUD DETALLE FACTURA *************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarDetalleFactura(prodIdFact INT, factIdFact INT)
BEGIN
    INSERT INTO detalleFactura(productoId, facturaId)
    VALUES (prodIdFact, factIdFact);
END$$
DELIMITER ;

CALL sp_agregarDetalleFactura(1, 1);

DELIMITER $$
CREATE PROCEDURE sp_ListarDetalleFactura()
BEGIN
    SELECT detalleFacturaId, 
        productoId, 
        facturaId
    FROM detalleFactura;
END$$
DELIMITER ;

CALL sp_ListarDetalleFactura();


DELIMITER $$
CREATE PROCEDURE sp_EliminarDetalleFactura(IN detFactId INT)
BEGIN
    DELETE FROM detalleFactura 
    WHERE detalleFacturaId = detFactId;
END$$
DELIMITER ;

CALL sp_EliminarDetalleFactura(2);


DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleFactura(IN detFactId INT)
BEGIN
    SELECT detalleFacturaId, 
        productoId, 
        facturaId
    FROM detalleFactura 
    WHERE detalleFacturaId = detFactId;
END$$
DELIMITER ;

CALL sp_BuscarDetalleFactura(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarDetalleFactura(IN detFactId INT, IN prodIdFact INT, IN factIdFact INT)
BEGIN
    UPDATE detalleFactura
    SET productoId = prodIdFact, facturaId = factIdFact
    WHERE detalleFacturaId = detFactId;
END$$
DELIMITER ;

CALL sp_EditarDetalleFactura(1, 1, 1);


-- CRUD PROMOCIONES *************************************************************************

DELIMITER $$
CREATE PROCEDURE sp_agregarPromocion(precioProm DECIMAL(10,2), descProm VARCHAR(100), fechaInicioProm DATE, fechaFinProm DATE, prodIdProm INT)
BEGIN
    INSERT INTO Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId)
    VALUES (precioProm, descProm, fechaInicioProm, fechaFinProm, prodIdProm);
END$$
DELIMITER ;

CALL sp_agregarPromocion(2200.00, 'Semana Tegnologica', '2024-04-01', '2024-04-30', 1);

DELIMITER $$
CREATE PROCEDURE sp_ListarPromociones()
BEGIN
    SELECT promocionId, 
		precioPromocion, 
		descripcionPromocion, 
		fechaInicio, 
		fechaFinalizacion, 
		productoId
	FROM Promociones;
END$$
DELIMITER ;

CALL sp_ListarPromociones();


DELIMITER $$
CREATE PROCEDURE sp_EliminarPromocion(IN promId INT)
BEGIN
    DELETE FROM Promociones 
    WHERE promocionId = promId;
END$$
DELIMITER ;

CALL sp_EliminarPromocion(1);


DELIMITER $$
CREATE PROCEDURE sp_BuscarPromocion(IN promId INT)
BEGIN
    SELECT promocionId, 
		precioPromocion, 
		descripcionPromocion, 
		fechaInicio, 
		fechaFinalizacion, 
		productoId
	FROM Promociones 
	WHERE promocionId = promId;
END$$
DELIMITER ;

CALL sp_BuscarPromocion(2);


DELIMITER $$
CREATE PROCEDURE sp_EditarPromocion(IN promId INT, IN precioProm DECIMAL(10,2), IN descProm VARCHAR(100), IN fechaInicioProm DATE, IN fechaFinProm DATE, IN prodIdProm INT)
BEGIN
    UPDATE Promociones
    SET precioPromocion = precioProm, descripcionPromocion = descProm, fechaInicio = fechaInicioProm, fechaFinalizacion = fechaFinProm, productoId = prodIdProm
    WHERE promocionId = promId;
END$$
DELIMITER ;

CALL sp_EditarPromocion(1, 1200, 'Promoción de verano', '2024-05-01', '2024-05-31', 2);

-- PROCEDIMIENTOS PARA FACTURAS
Delimiter $$
create procedure sp_asignarTotalFactura(in factId int, in totalFact decimal (10,2))
Begin
	Update facturas
		set total = totalFact
			where facturaId =factId; 
End $$
Delimiter ;

Delimiter $$
create procedure sp_modificarStock(in detaFactId int, in stockActual int)
begin
	Update productos
		set cantidadStock = stockActual
			where productoId = detaFactId;
end $$
Delimiter ;

Delimiter $$
create procedure sp_asignarTotalCompra(in compId int, in totalC decimal (10,2))
Begin
	Update compras
		set totalCompra = totalC
			where compraId =compId; 
End $$
Delimiter ;

Delimiter $$
create procedure sp_modificarStockCompra(in detaFactId int, in stockActual int)
begin
	Update productos
		set cantidadStock = stockActual
			where productoId = detaFactId;
end $$
Delimiter ;



