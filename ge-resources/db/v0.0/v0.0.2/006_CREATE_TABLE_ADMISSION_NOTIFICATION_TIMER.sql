CREATE TABLE notification (
    id bigserial PRIMARY KEY,
    notification_type_id INTEGER REFERENCES notification_type(id),
    arbitration_id BIGINT REFERENCES arbitration(id),
    create_at TIMESTAMP not null default now(),
    update_at TIMESTAMP,
    process_execution VARCHAR(255),
    message TEXT,
    status VARCHAR(50),
    recipient_user VARCHAR(255),
    priority INTEGER,
    origin VARCHAR(255),
    last_seen TIMESTAMP
);

CREATE TABLE timer (
    id SERIAL PRIMARY KEY,
    max_days INTEGER,
    min_days INTEGER,
    procedure_name VARCHAR(255)
);

INSERT INTO timer (id, max_days, min_days, procedure_name) VALUES (1, 21, 14, 'Admisiones');

ALTER TABLE users
ADD COLUMN arbitration_board_id BIGINT;

ALTER TABLE users
ADD CONSTRAINT fk_users_arbitration_board
FOREIGN KEY (arbitration_board_id)
REFERENCES arbitration_board(id);

ALTER TABLE login
ADD COLUMN arbitration_board_id BIGINT;

ALTER TABLE login
ADD CONSTRAINT fk_login_arbitration_board
FOREIGN KEY (arbitration_board_id) REFERENCES arbitration_board(id);