
-- PW 123
INSERT INTO usuario (usuario, cantidad_intentos, contrasenia, fecha_espera, nombre)
VALUES('rodri', 0, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',null,'Rodrigo');
INSERT INTO usuario (usuario, cantidad_intentos, contrasenia, fecha_espera, nombre)
VALUES('dai', 0, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',null,'Daiana');
INSERT INTO usuario (usuario, cantidad_intentos, contrasenia, fecha_espera, nombre)
VALUES('roman', 0, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',null,'Roman');
INSERT INTO usuario (usuario, cantidad_intentos, contrasenia, fecha_espera, nombre)
VALUES('frongi', 0, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',null,'Tomas');
INSERT INTO usuario (usuario, cantidad_intentos, contrasenia, fecha_espera, nombre)
VALUES('marian', 0, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',null,'Mariano');
INSERT INTO usuario (usuario, cantidad_intentos, contrasenia, fecha_espera, nombre)
VALUES('luciano', 0, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',null,'Luciano');
INSERT INTO usuario (usuario, cantidad_intentos, contrasenia, fecha_espera, nombre)
VALUES('dds', 0, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3',null,'DDS');


---------------------
---- MOCK EGRESO ----
---------------------

		    insert
    into
        item
        (descripcion)
    values
        ('Un item');
		--
		    insert
    into
        item
        (descripcion)
    values
        ('Otro item');
		--
		    insert
    into
        item
        (descripcion)
    values
        ('Item A');
		--
		    insert
    into
        item
        (descripcion)
    values
        ('Item B');
		--
		    insert
    into
        item
        (descripcion)
    values
        ('Item C');
		--
		    insert
    into
        item
        (descripcion)
    values
        ('Item D');
	    --
		 insert
    into
        categoria
        (descripcion)
    values
        ('Categoria A');
        insert
    into
        item_categorias
            (item_id, categorias_id)
        values
            (1, 1);
		--
		insert
    into
        categoria
        (descripcion)
    values
        ('Categoria B');
		--
		insert
    into
        categoria
        (descripcion)
    values
        ('Categoria C');
		--
		insert
    into
        categoria
        (descripcion)
    values
        ('Otra Categoria');
		--
		insert
    into
        item_categorias
            (item_id, categorias_id)
        values
            (1, 1);
		--
		insert
    into
        item_categorias
            (item_id, categorias_id)
        values
            (2, 2);
		--
		insert
    into
        item_categorias
            (item_id, categorias_id)
        values
            (3, 2);
		--
		insert
    into
        item_categorias
            (item_id, categorias_id)
        values
            (4, 4);
		--
		insert
    into
        item_categorias
            (item_id, categorias_id)
        values
            (5, 4);
		--
		insert
    into
        item_categorias
            (item_id, categorias_id)
        values
            (6, 1);
		--
		insert
    into
        categoria
        (descripcion)
    values
        ('Otra categoria mas');
		--

		insert
    into
        proveedor
        ( nombre_razon_social, dni, dtype)
    values
        ('Un proveedor', '123456', 'ProveedorPersona');
		--
		insert into pais
		        (id_api, descripcion)
    values
		('ID123', 'Argentina');
		--
		insert into moneda
		        (id_api, descripcion)
    values
		('ID123', 'Australes');
	--
		insert
    into
        documento_comercial
        (moneda_id, numero, pais_id, tipo_documento)
    values
        (1, 123456, 1, 1);
--
    insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (1, 'Otra empresa', '121221231', '123',  'razon_social',  5);
--
    insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (3, 'Una empresa', '121221232', '123',  'razon_social',  5);
--
    insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (4, 'Arcos Plateados', '121221233', '123',  'razon_social',  5);
--
    insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (5, 'MercadoLibre', '121221234', '123',  'razon_social',  5);
--
    insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (6, 'Carrefive', '121221235', '123',  'razon_social',  5);
--
insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (7, 'Totoshop', '121221236', '123',  'razon_social',  5);
--
insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (8, 'Burger Queen', '121221237', '123',  'razon_social',  5);
--
insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (9, 'Telecenter', '121221238', '123',  'razon_social',  5);
--
	insert
    into
		usuario_entidad
        (entidad_id, usuario_id)
	values
		(4,7);
--
    insert
    into
		usuario_entidad
        (entidad_id, usuario_id)
	values
		(5,6);
--
    insert
    into
		usuario_entidad
        (entidad_id, usuario_id)
	values
		(6,7);
--
    insert
    into
		usuario_entidad
        (entidad_id, usuario_id)
	values
		(7,6);
--
    insert
    into
		usuario_entidad
        (entidad_id, usuario_id)
	values
		(7,7);
--
    insert
    into
		usuario_entidad
        (entidad_id, usuario_id)
	values
		(8,6);
--
    insert
    into
		usuario_entidad
        (entidad_id, usuario_id)
	values
		(8,7);
--
	insert
    into
		medio_pago
		(instrumento_pago)
	values
		('TARJETA');
--
    insert
    into
        egreso
        (id, codigo_operacion, documento_comercial_id, entidad_realizadora_id, fecha_creacion, fecha_operacion_efectuada,
        cantidad_presupuestos_minimos,  es_validacion_correcta, proveedor_id, resultado_validacion,
		medio_pago_id)
    values
        (80, 123, 1, 1, '20200812', '20200812', 2, false, 1, null, 1);
--
    insert
    into
        detalle_operacion
        (cantidad, item_id, precio, operacion_efectuada_id)
    values
        (2, 1, 30, 80);


-----------------------
---- MOCK Mensajes ----
-----------------------

INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación fue exitosa', 1);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación no fue exitosa', 1);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación fue exitosa', 2);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación no fue exitosa', 2);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación fue exitosa', 3);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación no fue exitosa', 3);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Mensaje Largo', 'Hola, este es un mensaje suficientemente largo como para ver como queda si lo acorto en la pantalla de mensajes', 3);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación fue exitosa', 4);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación no fue exitosa', 4);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación fue exitosa', 5);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación no fue exitosa', 5);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Mensaje generico 1', 'Este es el mensaje generico 1 de la cuenta "luciano"', 6);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Mensaje generico 2', 'Este es el mensaje generico 2 de la cuenta "luciano"', 6);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Mensaje generico 3', 'Este es el mensaje generico 3 de la cuenta "luciano"', 6);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Mensaje generico 1', 'Este es el mensaje generico 1 de la cuenta "dds"', 7);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Mensaje generico 2', 'Este es el mensaje generico 2 de la cuenta "dds"', 7);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Mensaje generico 3', 'Este es el mensaje generico 3 de la cuenta "dds"', 7);

----------------------
---- MOCK INGRESO ----
----------------------

		insert
    into
        documento_comercial
        (moneda_id, numero, pais_id, tipo_documento)
    values
        (1, 151515, 1, 1);

    insert
    into
        entidad_juridica_empresa
        (id, nombre, cuit, codigo_igj,  razon_social, cantidad_personal)
    values
        (2, 'McDonalds', '20202020', '1234',  'razon_social-nueva',  10);

    insert
    into
        ingreso
        (codigo_operacion, documento_comercial_id, entidad_realizadora_id, fecha_creacion, descripcion, total,id)
    values
        (1, 2, 2, '20200812', 'Prueba Ingreso', 10101010,800);
    insert
    into
        usuario_entidad
        (entidad_id, usuario_id)
    values
         (2,1);
    insert
    into
        usuario_entidad
        (entidad_id, usuario_id)
    values
         (2,2);
    insert
    into
        usuario_entidad
        (entidad_id, usuario_id)
    values
         (2,3);
    insert
    into
        usuario_entidad
        (entidad_id, usuario_id)
    values
         (2,4);
    insert
    into
        usuario_entidad
        (entidad_id, usuario_id)
    values
         (2,5);

--------------------------
---- MOCK PRESUPUESTO ----
--------------------------

    insert
    into
        usuario_entidad
        (entidad_id, usuario_id)
    values
         (1,5);

    insert
    into
        presupuesto
        (id, codigo_operacion, documento_comercial_id, entidad_realizadora_id, fecha_creacion, egreso_id)
    values
        (7, 123, 1, 1, '20200812', 80);

    insert
    into
        presupuesto
        (id, codigo_operacion, documento_comercial_id, entidad_realizadora_id, fecha_creacion, egreso_id)
    values
        (4, 123, 1, 2, '20201020', 80);

    insert
    into
        detalle_precio
        (id, precio, detalle_operacion_id, presupuesto_id)
    values
        (1, 70, 1, 7);

    insert
    into
        detalle_precio
        (id, precio, detalle_operacion_id, presupuesto_id)
    values
        (2, 40, 1, 7);

    insert
    into
        detalle_precio
        (id, precio, detalle_operacion_id, presupuesto_id)
    values
        (7, 55, 1, 4);