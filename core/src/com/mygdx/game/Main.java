package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ecs.ECSEngine;
import com.mygdx.game.ecs.actors.DungeonActor;
import com.mygdx.game.ecs.actors.PlayerActor;
import com.mygdx.game.ecs.actors.TileActor;
import com.mygdx.game.ecs.systems.AnimationSystem;
import com.mygdx.game.ecs.systems.DungeonRenderingSystem;
import com.mygdx.game.ecs.systems.DungeonSystem;
import com.mygdx.game.ecs.systems.MovementSystem;
import com.mygdx.game.ecs.systems.SpriteRenderingSystem;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texSample;
	Texture texCharacter;
	Texture texTilemapWall;
	Texture texTilemapFloor;
	OrthographicCamera camera;
	int VIRTUAL_WIDTH = 1024;
	int VIRTUAL_HEIGHT = 768;
	private ECSEngine ecsEngine;
	
	@Override
	public void create () {
		VIRTUAL_WIDTH = Gdx.graphics.getWidth();
		VIRTUAL_HEIGHT = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(VIRTUAL_WIDTH,VIRTUAL_HEIGHT);

		camera.position.x = VIRTUAL_WIDTH/2;
		camera.position.y = VIRTUAL_HEIGHT/2;
		//camera.zoom -= 0.1;
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		texSample = new Texture("dungeon/example_v1.1.png");
		texTilemapWall = new Texture("dungeon/DawnLike/Objects/Wall.png");
		texTilemapFloor = new Texture("dungeon/DawnLike/Objects/Floor.png");
		texCharacter = new Texture("dungeon/chara_hero.png");
		ecsEngine = new ECSEngine();

		TileActor.createTileMapPreset(texTilemapWall,texTilemapFloor);
		PlayerActor.createPlayer(ecsEngine,5,5, PlayerActor.getAnimations(texCharacter));
		DungeonActor.createChunk(ecsEngine,-32,-32);
		ecsEngine.addSystem(new MovementSystem());
		ecsEngine.addSystem(new AnimationSystem());
		ecsEngine.addSystem(new DungeonSystem());
		ecsEngine.addSystem(new DungeonRenderingSystem(batch));
		ecsEngine.addSystem(new SpriteRenderingSystem(batch));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();


		batch.setProjectionMatrix(camera.combined);


		batch.begin();
		ecsEngine.update(Gdx.graphics.getRawDeltaTime());
		//batch.draw(texSample, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texSample.dispose();
	}



}
