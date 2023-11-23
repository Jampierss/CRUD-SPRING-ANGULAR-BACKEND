USE [BDMaestrosCapacitacion]
GO
INSERT [dbo].[cfg_tabla_auxiliar] ([cod_tabla_auxiliar], [fecha_crea], [id_usuario_crea], [ind_edicion], [nombre], [observacion], [sub_modulo_id]) VALUES (N'ESTGRL', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), 1, 0, N'ESTADO GENERAL', N'', NULL)
INSERT [dbo].[cfg_tabla_auxiliar] ([cod_tabla_auxiliar], [fecha_crea], [id_usuario_crea], [ind_edicion], [nombre], [observacion], [sub_modulo_id]) VALUES (N'TIPDID', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), 1, 0, N'TIPO DE DOCUMENTO DE IDENTIDAD', N'', NULL)
INSERT [dbo].[cfg_tabla_auxiliar_detalle] ([id], [abreviatura], [fecha_crea], [id_usuario_crea], [ind_habilitado], [nombre], [observacion], [valor], [valor2], [cod_tabla_auxiliar]) VALUES (0, N'INA', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), 1, 1, N'INACTIVO', N'', N'', N'', N'ESTGRL')
INSERT [dbo].[cfg_tabla_auxiliar_detalle] ([id], [abreviatura], [fecha_crea], [id_usuario_crea], [ind_habilitado], [nombre], [observacion], [valor], [valor2], [cod_tabla_auxiliar]) VALUES (1, N'ACT', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), 1, 1, N'ACTIVO', N'', N'', N'', N'ESTGRL')
INSERT [dbo].[cfg_tabla_auxiliar_detalle] ([id], [abreviatura], [fecha_crea], [id_usuario_crea], [ind_habilitado], [nombre], [observacion], [valor], [valor2], [cod_tabla_auxiliar]) VALUES (1, N'RUC', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), 1, 1, N'RUC', N'', N'', N'', N'TIPDID')
INSERT [dbo].[cfg_tabla_auxiliar_detalle] ([id], [abreviatura], [fecha_crea], [id_usuario_crea], [ind_habilitado], [nombre], [observacion], [valor], [valor2], [cod_tabla_auxiliar]) VALUES (2, N'DNI', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), 1, 1, N'DNI', N'', N'', N'', N'TIPDID')
INSERT [dbo].[cfg_tabla_auxiliar_detalle] ([id], [abreviatura], [fecha_crea], [id_usuario_crea], [ind_habilitado], [nombre], [observacion], [valor], [valor2], [cod_tabla_auxiliar]) VALUES (3, N'CNE', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), 1, 1, N'CARNET DE EXTRANJERIA', N'', N'', N'', N'TIPDID')
SET IDENTITY_INSERT [dbo].[seg_usuario] ON 

INSERT [dbo].[seg_usuario] ([id], [email], [enabled], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre_completo], [password], [username], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (1, N'gmail@gmail.com', 1, CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), NULL, 1, NULL, N'ADMIN', N'$2a$10$FL2UvBlx/WLsGUdOrCt6Teu.9WXt1bT4NLEvN8oGnmwyM1vKTwHPO', N'admin', 1, N'ESTGRL')
SET IDENTITY_INSERT [dbo].[seg_usuario] OFF
SET IDENTITY_INSERT [dbo].[mae_cargo] ON 

INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (1, N'', CAST(N'2021-08-13T00:00:00.0000000' AS DateTime2), NULL, 1, NULL, N'ADMINISTRADOR', NULL, 1, N'ESTGRL')
INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (2, N'', CAST(N'2021-08-13T00:00:00.0000000' AS DateTime2), NULL, 1, NULL, N'VENDEDOR', NULL, 1, N'ESTGRL')
INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (3, N'', CAST(N'2021-08-13T00:00:00.0000000' AS DateTime2), CAST(N'2021-08-17T11:37:47.2620000' AS DateTime2), 1, 1, N'CAJERO', NULL, 0, N'ESTGRL')
INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (4, N'', CAST(N'2021-08-13T00:00:00.0000000' AS DateTime2), CAST(N'2021-08-16T19:31:35.8180000' AS DateTime2), 1, 1, N'ALMACENERO', NULL, 1, N'ESTGRL')
INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (5, N'', CAST(N'2021-08-13T00:00:00.0000000' AS DateTime2), NULL, 1, NULL, N'SUPERVISOR', NULL, 1, N'ESTGRL')
INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (6, N'', CAST(N'2021-08-13T00:00:00.0000000' AS DateTime2), NULL, 1, NULL, N'GERENTE DE LOGISTICA', NULL, 1, N'ESTGRL')
INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (7, N'', CAST(N'2021-08-13T00:00:00.0000000' AS DateTime2), NULL, 1, NULL, N'GERENTE COMERCIAL', NULL, 1, N'ESTGRL')
INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (8, N'', CAST(N'2021-08-13T00:00:00.0000000' AS DateTime2), NULL, 1, NULL, N'GERENTE DE TI', NULL, 1, N'ESTGRL')
INSERT [dbo].[mae_cargo] ([id], [abreviatura], [fecha_crea], [fecha_modifica], [id_usuario_crea], [id_usuario_modifica], [nombre], [observacion], [estado_id], [estado_cod_tabla_auxiliar]) VALUES (10, NULL, NULL, NULL, NULL, NULL, N'GERENTE DE MARKETING', NULL, 1, N'ESTGRL')
SET IDENTITY_INSERT [dbo].[mae_cargo] OFF
SET IDENTITY_INSERT [dbo].[mae_cliente] ON 

INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (1, N'CLI1', N'', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), NULL, NULL, 1, NULL, N'CLIENTE 1', N'87461535', N'CLIENTE 1', 2, 1, N'ESTGRL', 2, N'TIPDID')
INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (2, N'CLI2', N'', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), NULL, NULL, 1, NULL, N'CLIENTE 2', N'43153466', N'CLIENTE 2', 3, 1, N'ESTGRL', 1, N'TIPDID')
INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (3, N'CLI3', N'', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), NULL, NULL, 1, NULL, N'CLIENTE 2', N'58483553', N'CLIENTE 3', 5, 1, N'ESTGRL', 2, N'TIPDID')
INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (4, N'CLI4', N'', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), CAST(N'2021-08-17T12:14:41.4460000' AS DateTime2), N'f337e205-9f7e-474d-b57e-29ea8b674cb4_1354312350151034056103213045254351035465402103.jpeg', 1, 1, N'CLIENTE 3', N'86431388', N'CLIENTE 4', 4, 1, N'ESTGRL', 1, N'TIPDID')
INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (5, N'CLI5', N'', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), CAST(N'2021-08-17T12:01:57.7720000' AS DateTime2), NULL, 1, 1, N'CLIENTE 4', N'87946646', N'CLIENTE 5', 5, 1, N'ESTGRL', 2, N'TIPDID')
INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (6, N'CLI6', N'', CAST(N'2021-07-14T12:02:30.3900000' AS DateTime2), CAST(N'2021-08-17T11:53:24.4830000' AS DateTime2), NULL, 1, 1, N'CLIENTE 5', N'93454464', N'CLIENTE 6', 2, 1, N'ESTGRL', 1, N'TIPDID')
INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (7, NULL, N'AVENIDA DIRECCION DE LA CALLE', CAST(N'2021-08-17T11:51:01.2950000' AS DateTime2), CAST(N'2021-08-17T11:51:08.2750000' AS DateTime2), NULL, 1, 1, N'CLIENTE 7', N'36253635215', N'ClIENTE 7', 1, 0, N'ESTGRL', 1, N'TIPDID')
INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (8, NULL, NULL, CAST(N'2021-08-17T11:54:09.2520000' AS DateTime2), NULL, NULL, 1, NULL, N'CLIENTE 9', N'11111111111111', N'CLIENTE 9', 2, 1, N'ESTGRL', 1, N'TIPDID')
INSERT [dbo].[mae_cliente] ([id], [abreviatura], [direccion], [fecha_crea], [fecha_modifica], [foto], [id_usuario_crea], [id_usuario_modifica], [nombre_comercial], [nro_documento_identidad], [razon_social], [cargo_id], [estado_id], [estado_cod_tabla_auxiliar], [tipo_documento_identidad_id], [tipo_documento_identidad_cod_tabla_auxiliar]) VALUES (9, NULL, NULL, CAST(N'2021-08-17T11:56:38.7260000' AS DateTime2), NULL, NULL, 1, NULL, N'CLIENTE 10', N'22222222222', N'CLIENTE 10', 8, 0, N'ESTGRL', 1, N'TIPDID')
SET IDENTITY_INSERT [dbo].[mae_cliente] OFF
SET IDENTITY_INSERT [dbo].[seg_rol] ON 

INSERT [dbo].[seg_rol] ([id], [nombre], [nombre_detallado]) VALUES (1, N'ROLE_TI', N'TI')
SET IDENTITY_INSERT [dbo].[seg_rol] OFF
INSERT [dbo].[seg_usuario_seg_rol_tevo] ([seg_usuario_id], [seg_rol_id]) VALUES (1, 1)
