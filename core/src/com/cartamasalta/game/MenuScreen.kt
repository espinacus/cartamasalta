package com.cartamasalta.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.cartamasalta.game.domain.Button

class MenuScreen(private val app: App) : ScreenAdapter() {
    private val camera = OrthographicCamera(App.WIDTH.toFloat(), App.HEIGHT.toFloat())
    private val playButton = Button(Assets.playButton, Rectangle(40f, 150f, 230f, 80f))
    private val touchPoint = Vector3()

    override fun show() {
        camera.position.set((App.WIDTH / 2).toFloat(), (App.HEIGHT / 2).toFloat(), 0f)
    }

    override fun render(delta: Float) {
        update()
        draw()
    }

    private fun update() {
        Gdx.input.justTouched().let {
            camera.unproject(touchPoint.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f))
            if (playButton.position.contains(touchPoint.x, touchPoint.y)) app.screen = GameScreen(app)
        }
    }

    private fun draw() {
        Gdx.gl.glClearColor(0F, 0F, 0F, 1F)
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        app.batch.projectionMatrix = camera.combined
        app.batch.enableBlending()

        app.batch.begin()
        app.batch.draw(Assets.background, 0f, 0f, App.WIDTH.toFloat(), App.HEIGHT.toFloat())
        app.batch.draw(playButton.image, playButton.position.x, playButton.position.y, playButton.position.width, playButton.position.height)
        app.batch.end()
    }
}