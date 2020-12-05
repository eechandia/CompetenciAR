
CREATE SCHEMA tp

CREATE TYPE TipoDoc AS
ENUM('DNI','PASAPORTE','PART_DE_NACIMIENTO');
CREATE TYPE EstadoCompetencia AS
ENUM('CREADA','PLANIFICADA','EN_DISPUTA','FINALIZADA','ELIMINADA');
CREATE TYPE TipoRonda AS
ENUM('LIGA','ELIMIN_SIMPLE','ELIMIN_DOBLE_GANADORES','ELIMIN_DOBLE_PERDEDORES');

	

	CREATE TABLE tp.pais
			(
			id SERIAL,
			nombre varchar(50),
			
			CONSTRAINT pk_idPais PRIMARY KEY (id)
		);	

	CREATE TABLE tp.provincia
		(
			id SERIAL,
			nombre varchar(50),
			id_pais integer,

			CONSTRAINT pk_idProvincia PRIMARY KEY (id),
			CONSTRAINT fk_idPais FOREIGN KEY (id_pais) REFERENCES tp.pais(id)
		);	

	CREATE TABLE tp.localidad
			(
			id SERIAL,
			nombre varchar(50),
			id_provincia integer,
			codigo_postal integer,
			
			CONSTRAINT pk_idLocalidad PRIMARY KEY (id),
			CONSTRAINT fk_idProvincia FOREIGN KEY (id_provincia) REFERENCES tp.provincia(id)
		);	


	CREATE TABLE tp.usuario
			(
			id SERIAL,
			correo_electronico varchar(150),
			contraseña varchar(50),
			nombre varchar(30),
			apellido varchar(30)
			tipo_documento TipoDoc,
			documento varchar(30),
			id_localidad integer,

			CONSTRAINT pk_idUsuario PRIMARY KEY (id),
			CONSTRAINT fk_idLocalidad FOREIGN KEY (id_localidad) REFERENCES tp.localidad(id)
		);	

	CREATE TABLE tp.deporte
			(
			id SERIAL,
			nombre varchar(50),	
			CONSTRAINT pk_idDeporte PRIMARY KEY (id)
		);	

		CREATE TABLE tp.sesion
			(
			id SERIAL,
			fecha_inicio date,
			fecha_fin date,
			id_usuario integer,

			CONSTRAINT pk_idSesion PRIMARY KEY (id),
			CONSTRAINT fk_idUsuario FOREIGN KEY (id_usuario) REFERENCES tp.usuario(id)
		);	



	CREATE TABLE tp.competencia
			(
			id SERIAL,
			nombre varchar(100),
			estado estado_competencia,
			reglamento varchar(100),
			dada_de_baja boolean,
			fecha_de_baja date,
			id_usuario integer,
			id_deporte integer,

			CONSTRAINT pk_idCompetencia PRIMARY KEY (id),
			CONSTRAINT fk_idUsuario FOREIGN KEY (id_usuario) REFERENCES tp.usuario(id),
			CONSTRAINT fk_idDeporte FOREIGN KEY (id_deporte) REFERENCES tp.deporte(id)
		);	

	CREATE TABLE tp.fixture
			(
			id SERIAL,
			id_competencia integer,
			CONSTRAINT pk_idFixture PRIMARY KEY (id),
			CONSTRAINT fk_idCompetencia FOREIGN KEY (id_competencia) REFERENCES tp.competencia(id)
		);	

	CREATE TABLE tp.modalidad
			(
			id SERIAL,
			id_competencia integer,
			CONSTRAINT pk_idModalidad PRIMARY KEY (id),
			CONSTRAINT fk_idCompetencia FOREIGN KEY (id_competencia) REFERENCES tp.competencia(id)
		);	

	CREATE TABLE tp.forma_puntuacion
			(
			id SERIAL,
			id_modalidad integer,
			CONSTRAINT pk_idFormaPuntuacion PRIMARY KEY (id),
			CONSTRAINT fk_idModalidad FOREIGN KEY (id_modalidad) REFERENCES tp.modalidad(id)
		);	

	CREATE TABLE tp.forma_puntuacion_sets
			(
			id SERIAL,
			cantidad_max_sets integer,
			CONSTRAINT pk_idFormaPuntuacionSets PRIMARY KEY (id),
			CONSTRAINT fk_idFormaPuntuacionSets FOREIGN KEY (id) REFERENCES tp.forma_puntuacion(id)
		);		
	
	CREATE TABLE tp.forma_puntuacion_puntuacion
			(
			id SERIAL,
			puntos_si_rival_ausente integer,
			CONSTRAINT pk_idFormaPuntuacionPuntuacion PRIMARY KEY (id),
			CONSTRAINT fk_idFormaPuntuacionPuntuacion FOREIGN KEY (id) REFERENCES tp.forma_puntuacion(id)
		);	

	CREATE TABLE tp.forma_puntuacion_res_final
			(
			id SERIAL,
			CONSTRAINT pk_idFormaPuntuacionResFinal PRIMARY KEY (id),
			CONSTRAINT fk_idFormaPuntuacionFinal FOREIGN KEY (id) REFERENCES tp.forma_puntuacion(id)
		);	

	CREATE TABLE tp.lugar_de_realizacion
			(
			codigo SERIAL,
			nombre varchar(100),
			descripcion varchar(200),
			disponibilidad integer,
			activo boolean,
			id_usuario integer,

			CONSTRAINT pk_CodigoFormaPuntuacionSets PRIMARY KEY (codigo),
			CONSTRAINT fk_idUsuarioLugarRealizacion FOREIGN KEY (id_usuario) REFERENCES tp.usuario(id)
		);	

	CREATE TABLE tp.participante
			(
			id SERIAL,
			correo_electronico varchar(150),
			nombre varchar(30),
			id_competencia integer,
		
			CONSTRAINT pk_idParticipante PRIMARY KEY (id),
			CONSTRAINT fk_idCompetenciaParticipante FOREIGN KEY (id_competencia) REFERENCES tp.competencia(id)
		);	

	CREATE TABLE tp.reserva
			(
			codigo_lugar_de_realizacion integer,
			id_competencia integer,
	
			CONSTRAINT pk_reserva PRIMARY KEY (codigo_lugar_de_realizacion, id_competencia),
			CONSTRAINT fk_idCompetenciaReserva FOREIGN KEY (id_competencia) REFERENCES tp.competencia(id),
			CONSTRAINT fk_idLugarDeRealizacionReserva FOREIGN KEY (codigo_lugar_de_realizacion) REFERENCES tp.lugar_de_realizacion(codigo)
		);	

	CREATE TABLE tp.sistema_competencia
			(
			id SERIAL,
			id_modalidad integer,
	
			CONSTRAINT pk_sistemaCompetencia PRIMARY KEY (id),
			CONSTRAINT fk_idModalidadSC FOREIGN KEY (id_modalidad) REFERENCES tp.modalidad(id)
		);	

	CREATE TABLE tp.sistema_de_eliminatoria_simple
			(
			id_sistema_competencia integer,
	
			CONSTRAINT pk_sistemaCompetenciaElminSimple PRIMARY KEY (id_sistema_competencia),
			CONSTRAINT fk_idSistemaCompetenciaElminSimple FOREIGN KEY (id_sistema_competencia) REFERENCES tp.sistema_competencia(id)
		);	

	CREATE TABLE tp.sistema_de_eliminatoria_doble
			(
			id_sistema_competencia integer,
	
			CONSTRAINT pk_sistemaCompetenciaElminDoble PRIMARY KEY (id_sistema_competencia),
			CONSTRAINT fk_idSistemaCompetenciaElminDoble FOREIGN KEY (id_sistema_competencia) REFERENCES tp.sistema_competencia(id)
		);	

	CREATE TABLE tp.sistema_de_liga
			(
			id_sistema_competencia integer,
			puntos_por_partido_Ganado integer,
			empate_permitido boolean,
			puntos_por_empate integer,
			puntos_por_presentarse integer,
	
			CONSTRAINT pk_sistemaCompetenciaLiga PRIMARY KEY (id_sistema_competencia),
			CONSTRAINT fk_idSistemaCompetenciaLiga FOREIGN KEY (id_sistema_competencia) REFERENCES tp.sistema_competencia(id)
		);	



	CREATE TABLE tp.deporte_lugar_de_realizacion
		(
			id_deporte integer,
			id_lugar_de_realizacion integer,

			CONSTRAINT pk_deporteLugarRealizacion PRIMARY KEY (id_deporte, id_lugar_de_realizacion),
			CONSTRAINT fk_DeporteLR FOREIGN KEY (id_deporte) REFERENCES tp.deporte(id),
			CONSTRAINT fk_LugarDeRealizacionD FOREIGN KEY (id_lugar_de_realizacion) REFERENCES tp.lugar_de_realizacion(codigo)
		);	


	CREATE TABLE tp.fecha
	(
		id_fecha integer,
		numero_fecha integer,
		id_fixture integer,
		ronda integer,
		CONSTRAINT pk_fecha PRIMARY KEY (id_fecha),
		CONSTRAINT fk_fixtureFecha FOREIGN KEY (id_fixture) REFERENCES tp.fixture(id)

	);

	CREATE TABLE tp.resultado
	(
		id_resultado integer,
		participante1_ganador boolean,
		participante2_ganador boolean,
		participante1_presente boolean,
		participante2_presente boolean,
		id_encuentro_deportivo integer,
		CONSTRAINT pk_resultado PRIMARY KEY (id_resultado)
	);



	CREATE TABLE tp.encuentro_deportivo
	(
		id_encuentro_deportivo integer,
		participante1 integer,
		participante2 integer,
		id_fecha integer,
		codigo_lugar_de_realizacion integer,
		id_resultado integer,
		id_siguiente_encuentro_ganador integer,
		id_siguiente_encuentro_perdedor integer,

		CONSTRAINT pk_encuentro_deportivo PRIMARY KEY (id_encuentro_deportivo),
		CONSTRAINT fk_encuentro_deportivo_participante1 FOREIGN KEY (participante1) REFERENCES tp.participante(id),
		CONSTRAINT fk_encuentro_deportivo_participante2 FOREIGN KEY (participante2) REFERENCES tp.participante(id),
		CONSTRAINT fk_encuentro_deportivo_fecha FOREIGN KEY (id_fecha) REFERENCES tp.fecha(id_fecha),
		CONSTRAINT fk_encuentro_deportivo_lugar_de_realizacion FOREIGN KEY (codigo_lugar_de_realizacion) REFERENCES tp.lugar_de_realizacion(codigo),
		CONSTRAINT fk_encuentro_deportivo_resultado FOREIGN KEY (id_resultado) REFERENCES tp.resultado(id_resultado),
		CONSTRAINT fk_encuentro_deportivo_siguienteganador FOREIGN KEY (id_siguiente_encuentro_ganador) REFERENCES tp.encuentro_deportivo(id_encuentro_deportivo),
		CONSTRAINT fk_encuentro_deportivo_siguienteperdedor FOREIGN KEY (id_siguiente_encuentro_perdedor) REFERENCES tp.encuentro_deportivo(id_encuentro_deportivo)
		);
	
	ALTER TABLE tp.resutlado
	ADD CONSTRAINT fk_resultado_encuentro FOREIGN KEY (id_encuentro_deportivo) REFERENCES tp.encuentro_deportivo(id_encuentro_deportivo)












