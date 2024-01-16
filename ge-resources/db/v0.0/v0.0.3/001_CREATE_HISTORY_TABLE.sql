CREATE TABLE history (
    id BIGSERIAL PRIMARY KEY,
    arbitration_id BIGINT NOT NULL,
	arbitration_board_id BIGINT NOT NULL,
    create_at TIMESTAMP NOT NULL,
	update_at TIMESTAMP NOT NULL,
    description TEXT,
    FOREIGN KEY (arbitration_id) REFERENCES arbitration(id) ON DELETE CASCADE,
	FOREIGN KEY (arbitration_board_id) REFERENCES arbitration_board(id) ON DELETE CASCADE
);

INSERT INTO public.arbitration_status(
	id, name, code)
	VALUES (9, 'Verificada', 'VERIFIED');

INSERT INTO public.arbitration_status(
	id, name, code)
	VALUES (10, 'Archivada', 'ARCHIVED');

INSERT INTO public.arbitration_status(
	id, name, code)
	VALUES (11, 'Subsanar', 'CORRECT');

INSERT INTO public.arbitration_status(
	id, name, code)
	VALUES (12, 'Firma de Subsanación', 'SIGNED_CORRECTION');
	
INSERT INTO public.arbitration_status(
	id, name, code)
	VALUES (13, 'Subsanada', 'CORRECT_FINISH');

INSERT INTO public.timer(
	id, max_days, min_days, procedure_name)
	VALUES (2, 10, 10, 'Subsanar');