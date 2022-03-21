import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.List;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    private final int WORLDWIDTH = getWidth();
    private final int WORLDHEIGHT = getHeight();
    
    Wall wallTemplate = new Wall();
    GreenfootImage wtImg = wallTemplate.getImage();
    private final int WALLHEIGHT = wtImg.getHeight();
    private final int WALLWIDTH = wtImg.getWidth();
    
    SmallBall smallBallTemplate = new SmallBall();
    GreenfootImage sbtImg = smallBallTemplate.getImage();
    
    BigBall bigBallTemplate = new BigBall();
    GreenfootImage bbtImg = bigBallTemplate.getImage();
    
    Cherry cherryTemplate = new Cherry();
    GreenfootImage ctImg = cherryTemplate.getImage();
    
    Straw strawTemplate = new Straw();
    GreenfootImage stImg = strawTemplate.getImage();
    
    PacmanHud hud = new PacmanHud();
    Pacman pacmanTemplate = new Pacman(hud);
    GreenfootImage ptImg = pacmanTemplate.getImage();
    
    private final int MAPWIDTH = (int) (WORLDWIDTH/WALLWIDTH);
    private final int MAPHEIGHT = (int) (WORLDHEIGHT/WALLHEIGHT);
    
    private final String FILENAME = "PacmanLevel1.txt";
    private final String WALLMARKER = "X";
    private final String SMALLBALLMARKER = "b";
    private final String BIGBALLMARKER = "B";
    private final String CHERRYMARKER = "C";
    private final String STRAWMARKER = "S";
    private final String PACMANMARKER = "P";
    String[][] mapArray = new String[MAPHEIGHT][MAPWIDTH];
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() throws IOException
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 550, 1); 
        
        mapArray = readMap(MAPWIDTH, MAPHEIGHT, FILENAME);
        
        drawWallMap(WALLWIDTH, WALLHEIGHT, WALLMARKER, SMALLBALLMARKER, BIGBALLMARKER, CHERRYMARKER, STRAWMARKER, PACMANMARKER, mapArray);
        addObject(hud,0,0);
        //drawSmallBall(WALLWIDTH, WALLHEIGHT, SMALLBALLMARKER, mapArray);
        
    }
    
    public void drawWallMap (int wallWidth, int wallHeight, String wallMarker, String smallBallMarker, String bigBallMarker, String cherryMarker, String strawMarker, String pacmanMarker, String[][] mapArray)
    {
        int x=0;
        int y=0;
        
        for(y=0; y<mapArray.length; y++)
        {
            for(x=0; x<mapArray[y].length; x++)
            {
                if(mapArray[y][x].equals(wallMarker))
                {
                    int wallX = x*wallWidth + wallWidth/2;
                    int wallY = y*wallHeight + wallHeight/2;
                    addObject(new Wall(), wallX, wallY);
                } else if(mapArray[y][x].equals(smallBallMarker))
                {
                    int smallBallX = x*wallWidth + wallWidth/2;
                    int smallBallY = y*wallHeight + wallHeight/2;
                    addObject(new SmallBall(), smallBallX, smallBallY);
                } else if(mapArray[y][x].equals(bigBallMarker))
                {
                    int bigBallX = x*wallWidth + wallWidth/2;
                    int bigBallY = y*wallHeight + wallHeight/2;
                    addObject(new BigBall(), bigBallX, bigBallY);
                } else if(mapArray[y][x].equals(cherryMarker))
                {
                    int cherryX = x*wallWidth + wallWidth/2;
                    int cherryY = y*wallHeight + wallHeight/2;
                    addObject(new Cherry(), cherryX, cherryY);
                } else if(mapArray[y][x].equals(strawMarker))
                {
                    int strawX = x*wallWidth + wallWidth/2;
                    int strawY = y*wallHeight + wallHeight/2;
                    addObject(new Straw(), strawX, strawY);
                } else if(mapArray[y][x].equals(pacmanMarker))
                {
                    int pacmanX = x*wallWidth + wallWidth/2;
                    int pacmanY = y*wallHeight + wallHeight/2;
                    addObject(new Pacman(hud), pacmanX, pacmanY);
                }
            }
        }
    }
    
    public String[][] readMap(int mapWidth, int mapHeight, String fileName) throws IOException  //possible problems with the file
    {
        BufferedReader br = null;  //Read one line at a time
        String [][] mArray = new String [mapHeight][mapWidth];  //become the mapArray
        try{
            br = new BufferedReader(new FileReader(fileName));
            String l; //Represent he line that we are reading at a particular time
            int mapRow = 0;  //Tell us the row we are on
            while((l=br.readLine())!=null)
            {
                mArray[mapRow] = l.split(""); //split into each character
                mapRow++;
            }
        } finally {
            if(br!=null)
                br.close();
        }
        return mArray;
    }
}
