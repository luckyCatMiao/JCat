package JCat.Render;

import JCat.Canvas.Canvas;
import JCat.Display.Bitmap;
import JCat.Display.DisplayObject;
import JCat.Display.DisplayObjectContainer;
import JCat.Display.MovieClip;
import JCat.Display.Stage;
import JCat.Graphics.Color;
import JCat.Textures.Texture;

public class Renderer {

	public void render(DisplayObject displayObject, Canvas canvas) {
		
		//dispatch event before render
		
		canvas.preRender();
		
		canvas.drawDefaultBackground(Color.ALICEBLUE);
				
		renderObject(displayObject,canvas);
				
		canvas.postRender();
				
		//dispatch event after render
		
	}

	
	/**
	 * render a object and it's child
	 * @param displayObject
	 * @param canvas 
	 */
	private void renderObject(DisplayObject displayObject, Canvas canvas) 
	{

		//render bitmap
		if(displayObject instanceof Bitmap)
		{
			Bitmap bitmap=(Bitmap) displayObject;
			
			if(bitmap.visible)
			{
				Texture texture=bitmap.getTexture();
				
				RenderData renderData=createRenderData(bitmap);
				//render the texture to screen
				canvas.drawTexture(texture,renderData);
			}
			
		}
		
		//render the current frame of the movieclip
		else if(displayObject instanceof MovieClip)
		{
			
		}
		
		//if it's a container,render it's childs
		else if(displayObject instanceof DisplayObjectContainer)
		{
			DisplayObjectContainer displayObjectContainer=(DisplayObjectContainer) displayObject;
			for(DisplayObject child:displayObjectContainer.getChilds())
			{
				renderObject(child,canvas);
			}
		}
		
	
	}


	private RenderData createRenderData(Bitmap bitmap) {
		
		RenderData renderData=new RenderData();
		renderData.transform=bitmap.getWorldTransform();
		renderData.alpha=bitmap.getWorldAlpha();
		
		return renderData;
	}
}
