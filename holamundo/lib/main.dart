import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Hola Mundo Flutter',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Mi Primera App'),
        ),
        body: const Center(
          child: Text(
            '¡Hola Mundo!',
            style: TextStyle(fontSize: 30),
          ),
        ),
     ),
  );
  }
}