INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('app.renova.veiculos', '3c82ec06693b3d54c583c120ada7cc8c1ba6ec836a2eec9ea7837b3c1cffa4c2', 'plataforma',
	'password,refresh_token,client_credentials', null, null, 36000, 36000, null, true);