CREATE TABLE videojuego (
                            id            SERIAL PRIMARY KEY,
                            titulo        VARCHAR(100) NOT NULL,
                            plataforma    VARCHAR(20)  NOT NULL,
                            genero        VARCHAR(30),
                            anio          INTEGER CHECK (anio BETWEEN 1970 AND 2030),
                            estado        VARCHAR(15)  NOT NULL
                                CHECK (estado IN ('PENDIENTE', 'JUGANDO', 'COMPLETADO')),
                            valoracion    INTEGER CHECK (valoracion BETWEEN 0 AND 10),
                            horas_jugadas INTEGER DEFAULT 0 CHECK (horas_jugadas >= 0)
);