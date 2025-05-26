const express = require('express');

const { ModelUsuario, modelusuario } = require('../../../domain/object/usuario/modelusuario');

var router = express.Router();

// GET
/**
 * @swagger
 * /operaciongetusuario:
 *   get:
 *     summary: Get all ModelUsuario
 *     responses:
 *       200:
 *         description: List of ModelUsuario
 */
router.get('/operaciongetusuario', async (req, res) => {
	// var idusuario = "1";
	var explanation = "102ds5455";
	var title = "Luis Miguel";
	// var apellidos = "Cortinez Rincon";
	var url = "";
	try {
		const modelusuario = new ModelUsuario(explanation, title, url);
		res.status(200).json(modelusuario);

	} catch (err) {
		res.status(500).json({ error: err.message });
	}
});

/**
 * @swagger
 * /operaciongetusuariodocumento:
 *   get:
 *     tags:
 *       - Datos Personales
 *     summary: Get ModelDatosPersonales by Documento
 *     parameters:
 *       - name: documento
 *         in: query
 *         description: Documento
 *         required: true
 *         schema:
 *           type: string
 *           default: 0
 *     responses:
 *       200:
 *         description: Respuesta exitosa
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 results:
 *                   type: array
 *                   items:
 *                     type: string
 *       404:
 *         description: Usuario no encontrado
 */
router.get('/operaciongetusuariodocumento', async (req, res) => {

	try {
		const { documento, nombre } = req.query;

		if (documento == '1037640956' && nombre == "miguel") {
			res.status(200).json({
				idusuario : "1",
				documento: "1037640956",
				nombres: "Luis Miguel",
				apellidos : "Cortinez",
				imagen : "https://media.licdn.com/dms/image/v2/D4E03AQHuIr8WIc2u3g/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1664919025590?e=2147483647&v=beta&t=XcnwtacQCCiLz_D6vUOVlDpiw6HoNQXnaENkqxzveDs"
			});
		}
		else {
			res.status(404).json({ message: 'Usuario no valido' });
		}
	} catch (err) {
		res.status(500).json({ error: err.message });
	}
});

module.exports = router;