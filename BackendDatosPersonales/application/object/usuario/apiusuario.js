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
				imagen : "https://media-bog2-1.cdn.whatsapp.net/v/t61.24694-24/491867909_9138957122875872_5802305768111151358_n.jpg?ccb=11-4&oh=01_Q5Aa1gHrqZYH8ay_4lI4dadMsFHziT2Qbs7fUBIbbiBbYu6PNQ&oe=6830C946&_nc_sid=5e03e0&_nc_cat=106"
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