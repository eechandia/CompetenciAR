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
			idPais integer,

			CONSTRAINT pk_idProvincia PRIMARY KEY (id),
			CONSTRAINT fk_idPais FOREIGN KEY (idPais) REFERENCES tp.pais(id)
		);	

	CREATE TABLE tp.localidad
			(
			id SERIAL,
			nombre varchar(50),
			idProvincia integer,
			codigoPostal integer,
			
			CONSTRAINT pk_idLocalidad PRIMARY KEY (id),
			CONSTRAINT fk_idProvincia FOREIGN KEY (idProvincia) REFERENCES tp.provincia(id)
		);	


	CREATE TABLE tp.usuario
			(
			id SERIAL,
			correoElectronico varchar(150),
			contraseña varchar(50),
			nombre varchar(30),
			apellido boolean,
			tipoDocumento TipoDoc,
			documento varchar(30),
			idLocalidad integer,

			CONSTRAINT pk_idUsuario PRIMARY KEY (id),
			CONSTRAINT fk_idLocalidad FOREIGN KEY (idLocalidad) REFERENCES tp.localidad(id)
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
			fechaInicio date,
			fechaFin date,
			idUsuario integer,

			CONSTRAINT pk_idSesion PRIMARY KEY (id),
			CONSTRAINT fk_idUsuario FOREIGN KEY (idUsuario) REFERENCES tp.usuario(id)
		);	



	CREATE TABLE tp.competencia
			(
			id SERIAL,
			nombre varchar(100),
			estado EstadoCompetencia,
			reglamento varchar(100),
			dadaDeBaja boolean,
			fechaDeBaja date,
			idUsuario integer,
			idDeporte integer,

			CONSTRAINT pk_idCompetencia PRIMARY KEY (id),
			CONSTRAINT fk_idUsuario FOREIGN KEY (idUsuario) REFERENCES tp.usuario(id),
			CONSTRAINT fk_idDeporte FOREIGN KEY (idDeporte) REFERENCES tp.deporte(id)
		);	

	CREATE TABLE tp.fixture
			(
			id SERIAL,
			idCompetencia integer,
			CONSTRAINT pk_idFixture PRIMARY KEY (id),
			CONSTRAINT fk_idCompetencia FOREIGN KEY (idCompetencia) REFERENCES tp.competencia(id)
		);	

	CREATE TABLE tp.modalidad
			(
			id SERIAL,
			idCompetencia integer,
			CONSTRAINT pk_idModalidad PRIMARY KEY (id),
			CONSTRAINT fk_idCompetencia FOREIGN KEY (idCompetencia) REFERENCES tp.competencia(id)
		);	

	CREATE TABLE tp.formaPuntuacion
			(
			id SERIAL,
			idModalidad integer,
			CONSTRAINT pk_idFormaPuntuacion PRIMARY KEY (id),
			CONSTRAINT fk_idModalidad FOREIGN KEY (idModalidad) REFERENCES tp.modalidad(id)
		);	

	CREATE TABLE tp.formapuntuacionsets
			(
			id SERIAL,
			cantidadMaxSets integer,
			CONSTRAINT pk_idFormaPuntuacionSets PRIMARY KEY (id),
			CONSTRAINT fk_idFormaPuntuacionSets FOREIGN KEY (id) REFERENCES tp.formapuntuacion(id)
		);		
	
	CREATE TABLE tp.formaPuntuacionPuntuacion
			(
			id SERIAL,
			puntosSiRivalAusente integer,
			CONSTRAINT pk_idFormaPuntuacionPuntuacion PRIMARY KEY (id),
			CONSTRAINT fk_idFormaPuntuacionPuntuacion FOREIGN KEY (id) REFERENCES tp.formapuntuacion(id)
		);	

	CREATE TABLE tp.formaPuntuacionResFinal
			(
			id SERIAL,
			CONSTRAINT pk_idFormaPuntuacionResFinal PRIMARY KEY (id),
			CONSTRAINT fk_idFormaPuntuacionFinal FOREIGN KEY (id) REFERENCES tp.formapuntuacion(id)
		);	

	CREATE TABLE tp.lugarDeRealizacion
			(
			codigo SERIAL,
			nombre varchar(100),
			descripcion varchar(200),
			activo boolean,
			idUsuario integer,

			CONSTRAINT pk_CodigoFormaPuntuacionSets PRIMARY KEY (codigo),
			CONSTRAINT fk_idUsuarioLugarRealizacion FOREIGN KEY (idUsuario) REFERENCES tp.usuario(id)
		);	

	CREATE TABLE tp.participante
			(
			id SERIAL,
			correoElectronico varchar(150),
			nombre varchar(30),
			idCompetencia integer,
		
			CONSTRAINT pk_idParticipante PRIMARY KEY (id),
			CONSTRAINT fk_idCompetenciaParticipante FOREIGN KEY (idCompetencia) REFERENCES tp.competencia(id)
		);	

	CREATE TABLE tp.reserva
			(
			codigoLugarDeRealizacion integer,
			idCompetencia integer,
	
			CONSTRAINT pk_reserva PRIMARY KEY (codigoLugarDeRealizacion, idCompetencia),
			CONSTRAINT fk_idCompetenciaReserva FOREIGN KEY (idCompetencia) REFERENCES tp.competencia(id),
			CONSTRAINT fk_idLugarDeRealizacionReserva FOREIGN KEY (codigoLugarDeRealizacion) REFERENCES tp.lugarDeRealizacion(codigo)
		);	

	CREATE TABLE tp.sistemaCompetencia
			(
			id SERIAL,
			idModalidad integer,
	
			CONSTRAINT pk_sistemaCompetencia PRIMARY KEY (id),
			CONSTRAINT fk_idModalidadSC FOREIGN KEY (idModalidad) REFERENCES tp.modalidad(id)
		);	

	CREATE TABLE tp.sistemaDeEliminatoriaSimple
			(
			idSistemaCompetencia integer,
	
			CONSTRAINT pk_sistemaCompetenciaElminSimple PRIMARY KEY (idSistemaCompetencia),
			CONSTRAINT fk_idSistemaCompetenciaElminSimple FOREIGN KEY (idSistemaCompetencia) REFERENCES tp.sistemaCompetencia(id)
		);	

	CREATE TABLE tp.sistemaDeEliminatoriaDoble
			(
			idSistemaCompetencia integer,
	
			CONSTRAINT pk_sistemaCompetenciaElminDoble PRIMARY KEY (idSistemaCompetencia),
			CONSTRAINT fk_idSistemaCompetenciaElminDoble FOREIGN KEY (idSistemaCompetencia) REFERENCES tp.sistemaCompetencia(id)
		);	

	CREATE TABLE tp.sistemaDeLiga
			(
			idSistemaCompetencia integer,
			puntosPorPartidoGanado integer,
			empatePermitido boolean,
			puntosPorEmpate integer,
			puntosPorPresentarse integer,
	
			CONSTRAINT pk_sistemaCompetenciaLiga PRIMARY KEY (idSistemaCompetencia),
			CONSTRAINT fk_idSistemaCompetenciaLiga FOREIGN KEY (idSistemaCompetencia) REFERENCES tp.sistemaCompetencia(id)
		);	



	CREATE TABLE tp.deporteLugarDeRealizacion
		(
			idDeporte integer,
			idLugarDeRealizacion integer,

			CONSTRAINT pk_deporteLugarRealizacion PRIMARY KEY (idDeporte, idLugarDeRealizacion),
			CONSTRAINT fk_DeporteLR FOREIGN KEY (idDeporte) REFERENCES tp.deporte(id),
			CONSTRAINT fk_LugarDeRealizacionD FOREIGN KEY (idLugarDeRealizacion) REFERENCES tp.lugarDeRealizacion(codigo)
		);	






