package com.example.ex_02_opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    Square myBox;
    Square1 myBox1;
    Square2 myBox2;
    Square3 myBox3;
    Square4 myBox4;
    Square5 myBox5;

    float [] mMVPMatrix = new float[16];
    float [] mProjectionMatrix = new float[16];
    float [] mViewMatrix = new float[16];

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        GLES20.glClearColor(0.0f, 1.0f, 1.0f, 1.0f);


        myBox = new Square();
        myBox1 = new Square1();
        myBox2 = new Square2();
        myBox3 = new Square3();
        myBox4 = new Square4();
        myBox5 = new Square5();
    }

    // 화면 갱신 되면서 화면에서 배치
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0,0, width, height);  // 이부분이 시작점을 지정하는 곳이라고 함.

        float ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0,-ratio,ratio,-1,1,3,10);
        }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        Matrix.setLookAtM(mViewMatrix, 0,
                // x, y, z
                3,-2,-6, // 카메라 위치
                0,0,0, // 카메라 시선
                0,1,0 // 카메라 정방향
        );

        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // 정사각형 그리기
//        myBox.draw(mMVPMatrix);
        myBox1.draw(mMVPMatrix);
        myBox2.draw(mMVPMatrix);
        myBox3.draw(mMVPMatrix);
        myBox4.draw(mMVPMatrix);
        myBox5.draw(mMVPMatrix);

    }

    // GPU를 이용하여 그리기를 연산한다.
    static int loadShader(int type, String shaderCode){

        int res = GLES20.glCreateShader(type);

        GLES20.glShaderSource(res, shaderCode);
        GLES20.glCompileShader(res);
        return res;

    }
}
