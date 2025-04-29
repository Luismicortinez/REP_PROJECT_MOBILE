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
	var	title = "Luis Miguel";
	// var apellidos = "Cortinez Rincon";
	var url = "";
	try {		
        const modelusuario = new ModelUsuario(explanation, title, url);
        res.status(200).json(modelusuario);	

	} catch (err) {
		res.status(500).json({ error: err.message });
	}
});

module.exports = router;