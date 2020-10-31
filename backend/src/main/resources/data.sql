
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


-- MOCK EGRESO
		--
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
        (1, 123456, 1, 1) ;
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
        (3, 'Una empresa', '121221231', '123',  'razon_social',  5);
--
	insert
    into
		usuario_entidad
        (entidad_id, usuario_id)
	values
		(1,1);
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
        (1, 123, 1, 1, '20200812', '20200812', 2, false, 1, null, 1);
--
    		    insert
    into
        detalle_operacion
        (cantidad, item_id, precio, operacion_efectuada_id)
    values
        (2, 1, 30, 1);


-- MOCK Mensajes
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
VALUES ('Resultado Validación', 'La validación fue exitosa', 4);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación no fue exitosa', 4);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación fue exitosa', 5);
INSERT INTO mensaje (asunto, cuerpo, usuario_id)
VALUES ('Resultado Validación', 'La validación no fue exitosa', 5);

-- MOCK INGRESO
		--
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
        (1, 2, 2, '20200812', 'Prueba Ingreso', 10101010,1);
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

