package com.luismiguelcotinez.formulariocedulajava;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "Universidad.db";
    private static final String TABLE_NAME = "Estudiantes";
    private static final String TABLE_NAME1 = "Catedras";
    private static final int DB_VERSION = 1;
    private static final String ID_COL = "id_estudiante";
    private static final String FIRST_COL = "nombre";
    private static final String SECOND_COL = "apellidos";
    private static final String THIRD_COL = "documento";
    private static final String FOURTH_COL = "email";
    private static final String ID_CATEDRA = "id_catedra";
    private static final String NOMBRE_CATEDRA = "nombre_catedra";
    private static final String HORARIO_CATEDRA = "horario";
    private static DBHandler instance;

    public DBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_COL + " TEXT, "
                + SECOND_COL + " TEXT, "
                + THIRD_COL + " TEXT, "
                + FOURTH_COL + " TEXT)";
        String query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + " ("
                + ID_CATEDRA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOMBRE_CATEDRA + " TEXT, "
                + HORARIO_CATEDRA + " TEXT)";
        db.execSQL(query);
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long ingresarEstudiante(String nombre,String apellidos,String documento,String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_COL, nombre);
        values.put(SECOND_COL, apellidos);
        values.put(THIRD_COL, documento);
        values.put(FOURTH_COL, email);
        long id = db.insert(TABLE_NAME, null , values);
        return id;
    }
    public long ingresarCatedra(String nombre,String horario)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE_CATEDRA, nombre);
        values.put(HORARIO_CATEDRA, horario);
        long id = db.insert(TABLE_NAME1, null , values);
        return id;
    }

    public String consultar(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {ID_COL, FIRST_COL,SECOND_COL,THIRD_COL,FOURTH_COL};
        Cursor cursor = db.query(TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        buffer.append("Estudiantes:\n");
        while (cursor.moveToNext())
        {
            int cid = cursor.getInt(0);
            String name = cursor.getString(1);
            String lastName = cursor.getString(2);
            String document = cursor.getString(3);
            String email = cursor.getString(4);
            buffer.append(cid + "   " + name + "   " + lastName + "   " + document + "   " + email + " \n");
        }
        buffer.append("\n\nCatedas:\n");
        String[] columns1 = {ID_CATEDRA, NOMBRE_CATEDRA,HORARIO_CATEDRA};
        Cursor cursor1 = db.query(TABLE_NAME1,columns1,null,null,null,null,null);
        while (cursor1.moveToNext())
        {
            //int cid = cursor.getInt(cursor.getColumnIndex(ID_COL));
            int cid = cursor1.getInt(0);
            String name = cursor1.getString(1);
            String horario = cursor1.getString(2);
            buffer.append(cid + "   " + name + "   " + horario + " \n");
        }
        cursor.close();
        cursor1.close();
        return buffer.toString();
    }
    public String consultarE(String document){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {ID_COL, FIRST_COL,SECOND_COL,THIRD_COL,FOURTH_COL};
        String selection = THIRD_COL + " = ?";
        String[] selectionArgs = {document};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        StringBuffer buffer= new StringBuffer();
        if (cursor.moveToFirst()) {
            do {
                int cid = cursor.getInt(0);
                String name = cursor.getString(1);
                String lastName = cursor.getString(2);
                String Document = cursor.getString(3);
                String email = cursor.getString(4);

                buffer.append(cid).append("   ").append(name).append("   ").append(lastName)
                        .append("   ").append(Document).append("   ").append(email).append("\n");
            } while (cursor.moveToNext());
        }

        cursor.close();
        return buffer.toString();
    }
    public String consultarC(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {ID_CATEDRA, NOMBRE_CATEDRA,HORARIO_CATEDRA};
        String selection = NOMBRE_CATEDRA + " = ?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query(TABLE_NAME1,columns,selection,selectionArgs,null,null,null);
        StringBuffer buffer= new StringBuffer();
        if (cursor.moveToFirst()) {
            do {
                int cid = cursor.getInt(0);
                String Name = cursor.getString(1);
                String Horario = cursor.getString(2);

                buffer.append(cid).append("   ").append(Name).append("   ").append(Horario).append("\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return buffer.toString();
    }
    public int eliminarE(String documento)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs ={documento};

        int count = db.delete(TABLE_NAME ,THIRD_COL + " = ?", whereArgs);
        return  count;
    }
    public int eliminarC(String documento)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs ={documento};

        int count = db.delete(TABLE_NAME1 ,NOMBRE_CATEDRA + " = ?", whereArgs);
        return  count;
    }
    public int actualizarE(String document , String newDoc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOURTH_COL, newDoc);
        String[] whereArgs= {document};
        int count = db.update(TABLE_NAME, contentValues, THIRD_COL + " = ?", whereArgs );
        return count;
    }
    public int actualizarC(String nombre,String newDoc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HORARIO_CATEDRA, newDoc);
        String[] whereArgs= {nombre};
        int count = db.update(TABLE_NAME1, contentValues, NOMBRE_CATEDRA + " = ?", whereArgs );
        return count;
    }
}
