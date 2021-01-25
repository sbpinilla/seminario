-- Generado por Oracle SQL Developer Data Modeler 20.3.0.283.0710
--   en:        2021-01-07 13:54:44 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE


CREATE TABLE acudiente (
    id_acudiente       NUMBER NOT NULL,
    nombres            VARCHAR2(200) NOT NULL,
    apellidos          VARCHAR2(200) NOT NULL,
    fecha_nacimiento   DATE NOT NULL,
    telefono           VARCHAR2(50) NOT NULL,
    direccion          VARCHAR2(200) NOT NULL,
    id_tipo_documento  NUMBER NOT NULL,
    documento          VARCHAR2(50) NOT NULL
);

ALTER TABLE acudiente ADD CONSTRAINT acudiente_pk PRIMARY KEY ( id_acudiente );

CREATE TABLE curso (
    id_curso  NUMBER NOT NULL,
    id_grado  NUMBER NOT NULL,
    nombre    VARCHAR2(200) NOT NULL,
    anio      NUMBER NOT NULL
);

ALTER TABLE curso ADD CONSTRAINT curso_pk PRIMARY KEY ( id_curso );

CREATE TABLE curso_materia_profesor (
    id_curso_materia_profesor  NUMBER NOT NULL,
    id_curso                   NUMBER NOT NULL,
    id_materia                 NUMBER NOT NULL,
    id_profesor                NUMBER NOT NULL
);

ALTER TABLE curso_materia_profesor ADD CONSTRAINT curso_materia_profesor_pk PRIMARY KEY ( id_curso_materia_profesor );

CREATE TABLE dia (
    id_dia  NUMBER NOT NULL,
    nombre  VARCHAR2(200) NOT NULL
);

ALTER TABLE dia ADD CONSTRAINT dia_pk PRIMARY KEY ( id_dia );

CREATE TABLE dia_hora (
    id_dia_hora  NUMBER NOT NULL,
    id_dia       NUMBER NOT NULL,
    id_hora      NUMBER NOT NULL
);

ALTER TABLE dia_hora ADD CONSTRAINT dia_hora_pk PRIMARY KEY ( id_dia_hora );

CREATE TABLE dia_hora_curso_materia_prof (
    id_dh_cmp                  NUMBER NOT NULL,
    id_dia_hora                NUMBER NOT NULL,
    id_curso_materia_profesor  NUMBER NOT NULL
);

ALTER TABLE dia_hora_curso_materia_prof ADD CONSTRAINT dia_hora_curso_materia_prof_pk PRIMARY KEY ( id_dh_cmp );

CREATE TABLE estudiante (
    id_estudiante      NUMBER NOT NULL,
    id_curso           NUMBER NOT NULL,
    nombres            VARCHAR2(200) NOT NULL,
    apellidos          VARCHAR2(200) NOT NULL,
    fecha_nacimiento   DATE NOT NULL,
    telefono           VARCHAR2(50) NOT NULL,
    direccion          VARCHAR2(200) NOT NULL,
    id_tipo_documento  NUMBER NOT NULL,
    documento          VARCHAR2(50) NOT NULL
);

ALTER TABLE estudiante ADD CONSTRAINT estudiante_pk PRIMARY KEY ( id_estudiante );

CREATE TABLE estudiante_acudiente (
    id_estudiante_acudiente  NUMBER NOT NULL,
    id_estudiante            NUMBER NOT NULL,
    id_acudiente             NUMBER NOT NULL,
    parentesco               VARCHAR2(200) NOT NULL
);

ALTER TABLE estudiante_acudiente ADD CONSTRAINT estudiante_acudiente_pk PRIMARY KEY ( id_estudiante_acudiente );

CREATE TABLE grado (
    id_grado  NUMBER NOT NULL,
    nombre    VARCHAR2(200) NOT NULL
);

ALTER TABLE grado ADD CONSTRAINT grado_pk PRIMARY KEY ( id_grado );

CREATE TABLE hora (
    id_hora  NUMBER NOT NULL,
    nombre   VARCHAR2(200) NOT NULL
);

ALTER TABLE hora ADD CONSTRAINT hora_pk PRIMARY KEY ( id_hora );

CREATE TABLE materia (
    id_materia  NUMBER NOT NULL,
    nombre      VARCHAR2(200) NOT NULL
);

ALTER TABLE materia ADD CONSTRAINT materia_pk PRIMARY KEY ( id_materia );

CREATE TABLE modulo (
    id_modulo  NUMBER NOT NULL,
    nombre     VARCHAR2(200) NOT NULL
);

ALTER TABLE modulo ADD CONSTRAINT modulo_pk PRIMARY KEY ( id_modulo );

CREATE TABLE modulo_rol (
    id_modulo_rol  NUMBER NOT NULL,
    id_modulo      NUMBER NOT NULL,
    id_rol         NUMBER NOT NULL
);

ALTER TABLE modulo_rol ADD CONSTRAINT modulo_rol_pk PRIMARY KEY ( id_modulo_rol );

CREATE TABLE profesor (
    id_profesor        NUMBER NOT NULL,
    nombres            VARCHAR2(200) NOT NULL,
    apellidos          VARCHAR2(200) NOT NULL,
    fecha_nacimiento   DATE NOT NULL,
    telefono           VARCHAR2(50) NOT NULL,
    direccion          VARCHAR2(200) NOT NULL,
    id_tipo_documento  NUMBER NOT NULL,
    documento          VARCHAR2(50) NOT NULL
);

ALTER TABLE profesor ADD CONSTRAINT profesor_pk PRIMARY KEY ( id_profesor );

CREATE TABLE rol (
    id_rol  NUMBER NOT NULL,
    nombre  VARCHAR2(200) NOT NULL
);

ALTER TABLE rol ADD CONSTRAINT rol_pk PRIMARY KEY ( id_rol );

CREATE TABLE tipo_documento (
    id_tipo_documento  NUMBER NOT NULL,
    nombre             VARCHAR2(200)
);

ALTER TABLE tipo_documento ADD CONSTRAINT tipo_documento_pk PRIMARY KEY ( id_tipo_documento );

CREATE TABLE usuario (
    id_usuario         NUMBER NOT NULL,
    id_rol             NUMBER NOT NULL,
    nombre_usuario     VARCHAR2(200) NOT NULL,
    clave              VARCHAR2(2000) NOT NULL,
    id_tipo_documento  NUMBER NOT NULL,
    documento          VARCHAR2(200)
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE acudiente
    ADD CONSTRAINT acudiente_tipo_documento_fk FOREIGN KEY ( id_tipo_documento )
        REFERENCES tipo_documento ( id_tipo_documento );

ALTER TABLE curso_materia_profesor
    ADD CONSTRAINT cmp_curso_fk FOREIGN KEY ( id_curso )
        REFERENCES curso ( id_curso );

ALTER TABLE curso_materia_profesor
    ADD CONSTRAINT cmp_materia_fk FOREIGN KEY ( id_materia )
        REFERENCES materia ( id_materia );

ALTER TABLE curso_materia_profesor
    ADD CONSTRAINT cmp_profesor_fk FOREIGN KEY ( id_profesor )
        REFERENCES profesor ( id_profesor );

ALTER TABLE curso
    ADD CONSTRAINT curso_grado_fk FOREIGN KEY ( id_grado )
        REFERENCES grado ( id_grado );

ALTER TABLE dia_hora_curso_materia_prof
    ADD CONSTRAINT dhcmp_cmp_fk FOREIGN KEY ( id_curso_materia_profesor )
        REFERENCES curso_materia_profesor ( id_curso_materia_profesor );

ALTER TABLE dia_hora_curso_materia_prof
    ADD CONSTRAINT dhcmp_dia_hora_fk FOREIGN KEY ( id_dia_hora )
        REFERENCES dia_hora ( id_dia_hora );

ALTER TABLE dia_hora
    ADD CONSTRAINT dia_hora_dia_fk FOREIGN KEY ( id_dia )
        REFERENCES dia ( id_dia );

ALTER TABLE dia_hora
    ADD CONSTRAINT dia_hora_hora_fk FOREIGN KEY ( id_hora )
        REFERENCES hora ( id_hora );

ALTER TABLE estudiante_acudiente
    ADD CONSTRAINT ea_acudiente_fk FOREIGN KEY ( id_acudiente )
        REFERENCES acudiente ( id_acudiente );

ALTER TABLE estudiante_acudiente
    ADD CONSTRAINT ea_estudiante_fk FOREIGN KEY ( id_estudiante )
        REFERENCES estudiante ( id_estudiante );

ALTER TABLE estudiante
    ADD CONSTRAINT estudiante_curso_fk FOREIGN KEY ( id_curso )
        REFERENCES curso ( id_curso );

ALTER TABLE estudiante
    ADD CONSTRAINT estudiante_tipo_documento_fk FOREIGN KEY ( id_tipo_documento )
        REFERENCES tipo_documento ( id_tipo_documento );

ALTER TABLE modulo_rol
    ADD CONSTRAINT modulo_rol_modulo_fk FOREIGN KEY ( id_modulo )
        REFERENCES modulo ( id_modulo );

ALTER TABLE modulo_rol
    ADD CONSTRAINT modulo_rol_rol_fk FOREIGN KEY ( id_rol )
        REFERENCES rol ( id_rol );

ALTER TABLE profesor
    ADD CONSTRAINT profesor_tipo_documento_fk FOREIGN KEY ( id_tipo_documento )
        REFERENCES tipo_documento ( id_tipo_documento );

ALTER TABLE usuario
    ADD CONSTRAINT usuario_rol_fk FOREIGN KEY ( id_rol )
        REFERENCES rol ( id_rol );

ALTER TABLE usuario
    ADD CONSTRAINT usuario_tipo_documento_fk FOREIGN KEY ( id_tipo_documento )
        REFERENCES tipo_documento ( id_tipo_documento );


CREATE SEQUENCE SEQ_ACUDIENTE INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_CURSO INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_CURSO_MATERIA_PROFESOR INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_DIA INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_DIA_HORA INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_DIA_HORA_CMP INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_ESTUDIANTE INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_ESTUDIANTE_ACUDIENTE INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_GRADO INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_HORA INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_MATERIA INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_MODULO INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_MODULO_ROL INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_PROFESOR INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_ROL INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_TIPO_DOCUMENTO INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;

CREATE SEQUENCE SEQ_USUARIO INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999999999999999999999999999 NOCYCLE NOCACHE NOORDER ;


--------------------------------------------------------
--  DDL for Trigger TR_ACUDIENTE_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TR_ACUDIENTE_USUARIO" 
 AFTER INSERT 
 on acudiente
 for each row
 begin
 insert into USUARIO VALUES ( SEQ_USUARIO.NextVal, 3,LOWER(substr(:new.NOMBRES,1,1))||LOWER(NVL(substr(:new.APELLIDOS,1,INSTR(:new.APELLIDOS,' ',1,1)),:new.APELLIDOS)),:new.DOCUMENTO, :new.ID_TIPO_DOCUMENTO, :new.DOCUMENTO);
 end tr_acudiente_usuario;
/
ALTER TRIGGER "TR_ACUDIENTE_USUARIO" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_ELI_ACUDIENTE_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TR_ELI_ACUDIENTE_USUARIO" 
 BEFORE DELETE 
 on acudiente
 for each row
 begin
    delete FROM usuario where documento = :old.DOCUMENTO and ID_TIPO_DOCUMENTO = :old.ID_TIPO_DOCUMENTO;
 end tr_eli_acudiente_usuario;

/
ALTER TRIGGER "TR_ELI_ACUDIENTE_USUARIO" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_ELI_ESTUDIANTE_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TR_ELI_ESTUDIANTE_USUARIO" 
 BEFORE DELETE 
 on estudiante
 for each row
 begin
    delete FROM usuario where documento = :old.DOCUMENTO and ID_TIPO_DOCUMENTO = :old.ID_TIPO_DOCUMENTO;
 end tr_eli_estudiante_usuario;



/
ALTER TRIGGER "TR_ELI_ESTUDIANTE_USUARIO" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_ELI_PROFESOR_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TR_ELI_PROFESOR_USUARIO" 
 BEFORE DELETE 
 on profesor
 for each row
 begin
    delete FROM usuario where documento = :old.DOCUMENTO and ID_TIPO_DOCUMENTO = :old.ID_TIPO_DOCUMENTO;
 end tr_profesor_usuario;



/
ALTER TRIGGER "TR_ELI_PROFESOR_USUARIO" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_ESTUDIANTE_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TR_ESTUDIANTE_USUARIO" 
 AFTER INSERT 
 on estudiante
 for each row
 begin
  insert into USUARIO VALUES ( SEQ_USUARIO.NextVal, 2, LOWER(substr(:new.NOMBRES,1,1))||LOWER(NVL(substr(:new.APELLIDOS,1,INSTR(:new.APELLIDOS,' ',1,1)),:new.APELLIDOS)),:new.DOCUMENTO, :new.ID_TIPO_DOCUMENTO, :new.DOCUMENTO);
 end tr_estudiante_usuario;
/
ALTER TRIGGER "TR_ESTUDIANTE_USUARIO" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TR_PROFESOR_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "TR_PROFESOR_USUARIO" 
 AFTER INSERT 
 on profesor
 for each row
 begin
  insert into USUARIO VALUES ( SEQ_USUARIO.NextVal, 5, LOWER(substr(:new.NOMBRES,1,1))||LOWER(NVL(substr(:new.APELLIDOS,1,INSTR(:new.APELLIDOS,' ',1,1)),:new.APELLIDOS)),:new.DOCUMENTO, :new.ID_TIPO_DOCUMENTO, :new.DOCUMENTO);
 end tr_profesor_usuario;
/
ALTER TRIGGER "TR_PROFESOR_USUARIO" ENABLE;

 

 
------------------- SP -------------------
------------------------------------------
 
 create or replace PROCEDURE SP_GENERA_HORARIO(V_TIPO_DOC IN NUMBER,
							V_DOCUMENTO IN VARCHAR2,
							V_CODIGO OUT NUMBER,
							V_ERROR OUT VARCHAR2,
							V_CURSOR OUT SYS_REFCURSOR )
AS

V_HAY_TIPO_DOC NUMBER(1);
V_HAY_DOC NUMBER;
BEGIN
  --Validar si el tipo de documento ingresado existe
  SELECT COUNT(*) 
  INTO V_HAY_TIPO_DOC
  FROM TIPO_DOCUMENTO
  WHERE ID_TIPO_DOCUMENTO = V_TIPO_DOC;
  IF V_HAY_TIPO_DOC > 0 THEN
    --Validar si el documento ingresado existe
    SELECT COUNT(*)
    INTO V_HAY_DOC
    FROM ESTUDIANTE
    WHERE DOCUMENTO = V_DOCUMENTO
    AND ID_TIPO_DOCUMENTO = V_TIPO_DOC;
    IF V_HAY_DOC >0 THEN 
      --Realizar la consulta del horario
      BEGIN
        OPEN V_CURSOR FOR
          SELECT E.NOMBRES NOMBRES_ESTUDIANTE,
                 E.APELLIDOS APELLIDOS_ESTUDIANTE,
                 C.NOMBRE CURSO,--
                 --CMP.ID_CURSO_MATERIA_PROFESOR CMP,--
                 P.NOMBRES NOMBRES_PROFESOR,
                 P.APELLIDOS APELLIDOS_PROFESOR,
                 M.NOMBRE NOMBRE_MATERIA,
                 --DHCMP.ID_DH_CMP, --
                 --DH.ID_HORA, --
                 D.NOMBRE DIA,
                 H.NOMBRE HORA,
                 (SUBSTR(H.NOMBRE,1,2)+0) HORA_INICIO,
                 (SUBSTR(H.NOMBRE,1,2)+1) HORA_FIN
          FROM ESTUDIANTE E RIGHT JOIN CURSO C
            ON (C.ID_CURSO = E.ID_CURSO) 
          RIGHT JOIN CURSO_MATERIA_PROFESOR CMP 
            ON (CMP.ID_CURSO = C.ID_CURSO) 
          RIGHT JOIN PROFESOR P
            ON (P.ID_PROFESOR = CMP.ID_PROFESOR)
          RIGHT JOIN MATERIA M 
            ON M.ID_MATERIA = CMP.ID_MATERIA 
          RIGHT JOIN DIA_HORA_CURSO_MATERIA_PROF DHCMP
            ON DHCMP.ID_CURSO_MATERIA_PROFESOR = CMP.ID_CURSO_MATERIA_PROFESOR 
          RIGHT JOIN DIA_HORA DH
            ON DH.ID_DIA_HORA = DHCMP.ID_DIA_HORA 
          RIGHT JOIN DIA D
            ON D.ID_DIA = DH.ID_DIA 
          RIGHT JOIN HORA H
            ON H.ID_HORA = DH.ID_HORA
          WHERE E.ID_TIPO_DOCUMENTO = V_TIPO_DOC
          AND E.DOCUMENTO = V_DOCUMENTO
          ORDER BY D.ID_DIA,H.ID_HORA;

          V_CODIGO        := 1;
          V_ERROR := 'Mostrando resultados';
      EXCEPTION WHEN OTHERS THEN

        V_CODIGO        := 0;
        V_ERROR := 'No hay datos';
        OPEN V_CURSOR FOR SELECT NULL FROM dual;
      END;
    ELSE
      V_CODIGO        := -1;
      V_ERROR := 'No existe estudiante con el documento '||V_DOCUMENTO;
      OPEN V_CURSOR FOR SELECT NULL FROM dual;
    END IF;
  ELSE
    V_CODIGO        := -2;
    V_ERROR := 'No existe el tipo de ducumento ingresado';
    OPEN V_CURSOR FOR SELECT NULL FROM dual;
  END IF;
END;

------------------- SP -------------------
------------------------------------------

 
 
 


