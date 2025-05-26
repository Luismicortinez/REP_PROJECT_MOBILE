import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Formulario Datos Personales',
      theme: ThemeData(
        primarySwatch: Colors.deepPurple,
        scaffoldBackgroundColor: Color(0xFFF6F5FA),
        inputDecorationTheme: InputDecorationTheme(
          filled: true,
          fillColor: Colors.white,
          border: OutlineInputBorder(borderRadius: BorderRadius.circular(10)),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(10),
            borderSide: BorderSide(color: Colors.deepPurple),
          ),
        ),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            backgroundColor: Colors.deepPurple,
            padding: EdgeInsets.symmetric(horizontal: 24, vertical: 12),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10),
            ),
          ),
        ),
      ),
      home: FormularioPage(),
    );
  }
}

class FormularioPage extends StatefulWidget {
  const FormularioPage({super.key});

  @override
  _FormularioPageState createState() => _FormularioPageState();
}

class _FormularioPageState extends State<FormularioPage> {
  final TextEditingController _documentoController = TextEditingController();
  final TextEditingController _nombresController = TextEditingController();
  final TextEditingController _apellidosController = TextEditingController();
  String _resultado = "";

  late Database db;

  @override
  void initState() {
    super.initState();
    inicializarDB();
  }

  Future<void> inicializarDB() async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, 'datos_personales_71764044.db');

    db = await openDatabase(
      path,
      version: 1,
      onCreate: (db, version) async {
        await db.execute('''
          CREATE TABLE usuarios (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            documento TEXT,
            nombres TEXT,
            apellidos TEXT
          )
        ''');
      },
    );
  }

  Future<void> guardarDatos() async {
    await db.insert('usuarios', {
      'documento': _documentoController.text,
      'nombres': _nombresController.text,
      'apellidos': _apellidosController.text,
    });

    _documentoController.clear();
    _nombresController.clear();
    _apellidosController.clear();
  }

  Future<void> mostrarDatos() async {
    final List<Map<String, dynamic>> datos = await db.query('usuarios');
    setState(() {
      _resultado = datos
          .map((e) => '${e['documento']} - ${e['nombres']} ${e['apellidos']}')
          .join('\n');
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Formulario Datos Personales'),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          children: [
            Text(
              'Formulario Datos Personales',
              style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 20),
            TextField(
              controller: _documentoController,
              decoration: InputDecoration(
                labelText: 'Documento',
                prefixIcon: Icon(Icons.badge),
              ),
            ),
            SizedBox(height: 12),
            TextField(
              controller: _nombresController,
              decoration: InputDecoration(
                labelText: 'Nombres',
                prefixIcon: Icon(Icons.person),
              ),
            ),
            SizedBox(height: 12),
            TextField(
              controller: _apellidosController,
              decoration: InputDecoration(
                labelText: 'Apellidos',
                prefixIcon: Icon(Icons.person_outline),
              ),
            ),
            SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                ElevatedButton.icon(
                  onPressed: guardarDatos,
                  icon: Icon(Icons.save),
                  label: Text('Guardar'),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: const Color.fromARGB(255, 108, 150, 120),
                  ),
                ),
                ElevatedButton.icon(
                  onPressed: mostrarDatos,
                  icon: Icon(Icons.visibility),
                  label: Text('Mostrar'),
                  style: ElevatedButton.styleFrom(
                    backgroundColor: const Color.fromARGB(255, 108, 150, 120),
                  ),
                ),
              ],
            ),
            SizedBox(height: 20),
            Text('Resultados:', style: TextStyle(fontWeight: FontWeight.bold)),
            SizedBox(height: 10),
            Expanded(
              child: Container(
                padding: EdgeInsets.all(12),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(10),
                  border: Border.all(color: Colors.grey.shade300),
                ),
                child: SingleChildScrollView(
                  child: Text(
                    _resultado.isNotEmpty ? _resultado : 'No hay datos aún',
                    style: TextStyle(fontSize: 14),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
