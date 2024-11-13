package com.example.avance02_proyecto.Adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class plano extends View {

    private Paint paintWall, paintBorde,paintSeleccion, paintWindow, paintText;
    private Rect calleGranadaRect, claustroNoviciasRect, patioSilencioRect,claustroNaranjosRect, claustroMayorRect,pinacotecaRect,coroBajoRect,santaCatalinaRect,nuevoMonasterioRect,piletaRect,toledoRect,sevillaRect,cordovaRect; // Ejemplo de áreas seleccionables


    public interface OnAreaClickListener {
        void onAreaClicked(String areaName);
    }
    private OnAreaClickListener listener;


    public plano(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        // Configuración del pincel para las paredes y los cuadros
        paintWall = new Paint();
        paintWall.setColor(Color.LTGRAY);
        paintWall.setStyle(Paint.Style.FILL);
        paintWall.setStrokeWidth(8f);

        //Para el borde
        paintBorde= new Paint();
        paintBorde.setColor(Color.LTGRAY);
        paintBorde.setStyle(Paint.Style.STROKE);
        paintBorde.setStrokeWidth(8f);

        // Configuración de area definida para seleccionar
        paintSeleccion= new Paint();
        paintSeleccion.setColor(Color.TRANSPARENT);
        paintSeleccion.setStyle(Paint.Style.FILL);
        paintSeleccion.setStrokeWidth(4f);

        // Configuración del pincel del circulo celeste
        paintWindow = new Paint();
        paintWindow.setColor(Color.CYAN);
        paintWindow.setStyle(Paint.Style.FILL);

        // Paint para texto
        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(15f);
        paintText.setAntiAlias(true);


        // Definición de áreas seleccionables
        calleGranadaRect = new Rect(130, 600, 170, 800);
        claustroNoviciasRect = new Rect(410, 40, 540, 110);
        patioSilencioRect = new Rect(260, 40, 390, 110);
        claustroNaranjosRect = new Rect(260, 140, 340, 220);
        claustroMayorRect = new Rect(130, 140, 190, 350);
        pinacotecaRect = new Rect(200, 110, 250, 350);
        coroBajoRect = new Rect(50, 110, 120, 350);
        santaCatalinaRect = new Rect(50, 350, 120, 800);
        nuevoMonasterioRect = new Rect(340, 230, 550, 550);
        piletaRect = new Rect();
        toledoRect = new Rect(190, 600, 300, 800);
        sevillaRect = new Rect(140, 810, 300, 860);
        cordovaRect = new Rect(250, 350, 300, 500);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Ajustar escala
        int width = getWidth();
        int height = getHeight();
        float scaleFactor = Math.min(width / 600f, height / 1000f);

        canvas.scale(scaleFactor, scaleFactor);

        //Borde del plano
        canvas.drawRect(50, 20, 550, 1100, paintBorde);

        //Areas definidas para seleccionar
        canvas.drawRect(claustroNoviciasRect, paintSeleccion);//Area Clautro Novicias
        canvas.drawRect(patioSilencioRect, paintSeleccion);//Area Patio del silencio
        canvas.drawRect(claustroNaranjosRect, paintSeleccion);//Area Claustro de los naranjos
        canvas.drawRect(claustroMayorRect, paintSeleccion);//Area Claustro Mayor
        canvas.drawRect(piletaRect, paintWall); //Area Pinacoteca
        canvas.drawRect(coroBajoRect, paintWall);  //Area CoroBajo
        canvas.drawRect(santaCatalinaRect, paintWall); //Area Iglesia de santa catalina
        canvas.drawRect(nuevoMonasterioRect, paintWall); //Area Nuevo Monasterio
        canvas.drawCircle(138, 565, 15, paintWindow); //Area de la pileta
        canvas.drawRect(calleGranadaRect, paintSeleccion); //Area de calle Granada
        canvas.drawRect(toledoRect, paintSeleccion); //Area calle Toledo
        canvas.drawRect(sevillaRect, paintSeleccion);//Area Calle Sevilla
        canvas.drawRect(cordovaRect, paintSeleccion);//Area Calle Cordova


        //Resto de planos dibujados
        canvas.drawRect(50, 20, 550, 55, paintWall);
        canvas.drawRect(325, 110, 550, 210, paintWall);
        canvas.drawRect(50, 110, 300, 140, paintWall);
        canvas.drawRect(500, 20, 550, 210, paintWall);
        canvas.drawRect(380, 20, 430, 100, paintWall);
        canvas.drawRect(200, 110, 250, 350, paintWall);
        canvas.drawRect(270, 230, 550, 550, paintWall);
        canvas.drawRect(150, 370, 250, 550, paintWall);
        canvas.drawRect(150, 580, 250, 800, paintWall);
        canvas.drawRect(150, 850, 250, 980, paintWall);
        canvas.drawRect(270, 850, 400, 1020, paintWall);
        canvas.drawRect(270, 500, 400, 800, paintWall);


        //A partir de aqui solo se definio los textos
        //Claustro Noivias
        canvas.drawText("Claustro", 435, 80, paintText);
        canvas.drawText("Novicias", 435, 100, paintText);

        //Patio del silencio
        canvas.drawText("Patio del", 300, 80, paintText);
        canvas.drawText("Silencio", 305, 100, paintText);
        //Claustro Mayor
        canvas.drawText("Claustro", 130, 250, paintText);
        canvas.drawText("Mayor", 130, 270, paintText);
        //Claustro de los naranjos
        canvas.drawText("Claustro", 260, 170, paintText);
        canvas.drawText("de los", 260, 190, paintText);
        canvas.drawText("Naranjos", 260, 210, paintText);
        //Nuevo Monasterio
        canvas.drawText("Nuevo Monasterio", 360, 380, paintText);
        //Calle sevilla
        canvas.drawText("Calle sevilla", 160, 830, paintText);

        //A partir de aqui los textos tienen rotacion
        canvas.rotate(90, 75, 130);
        //coro Bajo
        canvas.drawText("Coro Bajo", 75, 130, paintText);
        //Iglesia de Santa Cartalina
        canvas.drawText("Iglesia Santa Catalina", 400, 130, paintText);
        //Pinacoteca
        canvas.drawText("Pinacoteca", 150, -15, paintText);
        //Calle cordova
        canvas.drawText("Calle cordova", 300, -50, paintText);
        //Plaza zocodober
        //Calle granada
        canvas.drawText("Calle Granada", 600, 70, paintText);
        //Cale toledo
        canvas.drawText("Calle toledo", 650, -50, paintText);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            // Ajustar escala
            int width = getWidth();
            int height = getHeight();
            float scaleFactor = Math.min(width / 600f, height / 1000f);

            float x = event.getX() / scaleFactor;
            float y = event.getY() / scaleFactor;

            // Verifica en qué área se hizo clic y llama a la interfaz
            if (calleGranadaRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Calle Granada");
            } else if (claustroNoviciasRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Claustro Novicias");
            } else if (patioSilencioRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Patio del Silencio");
            } else if (claustroNaranjosRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Claustro de los Naranjos");
            }else if (claustroMayorRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Claustro Mayor");
            }else if (pinacotecaRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Pinacoteca");
            }else if (coroBajoRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Coro Bajo");
            }else if (santaCatalinaRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Iglesia Santa Catalina");
            }else if (nuevoMonasterioRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Nuevo Monasterio");
            }else if (toledoRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Calle Toledo");
            }else if (sevillaRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Calle Sevilla");
            }else if (cordovaRect.contains((int) x, (int) y)) {
                if (listener != null) listener.onAreaClicked("Calle Cordova");
            }
        }
        return true;
    }

    public void setOnAreaClickListener(OnAreaClickListener listener) {
        this.listener = listener;
    }
}