package prj5;

import CS2114.WindowSide;
import java.awt.Color;
import CS2114.Button;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;

/**
 * 
 * @author Group54
 * @version 2016.04.11
 */
public class GUISongWindow {
    // Fields:

    LinkedList<Glyph> glyphs;
    int listPos = 0;
    
    /**
     * 
     */
    Window mainWindow;
    
    /**
     * 
     */
    Button previous;

    /**
     * 
     */
    Button next;

    /**
     * 
     */
    Button sortByName;

    /**
     * 
     */
    Button sortByGenre;

    /**
     * 
     */
    Button sortBySongTitle;

    /**
     * 
     */
    Button sortByReleaseYear;

    /**
     * 
     */
    Button representHobby;

    /**
     * 
     */
    Button representMajor;

    /**
     * 
     */
    Button representRegion;

    /**
     * 
     */
    Button quit;

    /**
     * 
     */
    SongList ourList;

    /**
     * 
     */
    Key mainKey;

    /**
     * 
     */
    public GUISongWindow(SongList list) {
        // Window Instantiation.
        mainWindow = new Window("Project 5");

        // SongList Instantiation.
        ourList = list;

        glyphs = new LinkedList<Glyph>();
        
        mainKey = new Key(650, 100);

        // Button Instantiation.
        previous = new Button("⬅ Previous");
        next = new Button("Next ➡");
        sortByName = new Button("Sort By Artist Name");
        sortByGenre = new Button("Sort By Genre");
        sortBySongTitle = new Button("Sort by Song Title");
        sortByReleaseYear = new Button("Sort by Release Year");
        representHobby = new Button("Represent Hobby");
        representMajor = new Button("Represent Major");
        representRegion = new Button("Represent Region");
        quit = new Button("Quit");

        // Button ToDo when pushed.

        previous.onClick(this, "clickedPrevious");
        next.onClick(this, "clickedNext");
        sortByName.onClick(this, "clickedSortByName");
        sortByGenre.onClick(this, "clickedSortByGenre");
        sortBySongTitle.onClick(this, "clickedSortBySongTitle");
        sortByReleaseYear.onClick(this, "clickedSortByReleaseYear");
        representHobby.onClick(this, "clickedRepresentHobby");
        representMajor.onClick(this, "clickedRepresentMajor");
        representRegion.onClick(this, "clickedRepresentRegion");
        quit.onClick(this, "clickedQuit");

        // Button Addition.

        mainWindow.addButton(previous, WindowSide.NORTH);
        mainWindow.addButton(sortByName, WindowSide.NORTH);
        mainWindow.addButton(sortBySongTitle, WindowSide.NORTH);
        mainWindow.addButton(sortByReleaseYear, WindowSide.NORTH);
        mainWindow.addButton(sortByGenre, WindowSide.NORTH);
        mainWindow.addButton(next, WindowSide.NORTH);

        mainWindow.addButton(representHobby, WindowSide.SOUTH);
        mainWindow.addButton(representMajor, WindowSide.SOUTH);
        mainWindow.addButton(representRegion, WindowSide.SOUTH);
        mainWindow.addButton(quit, WindowSide.SOUTH);
        
        checkButtons();

    }

    // Button Pushed Methods.

    /**
     * 
     */
    public void clickedPrevious(Button prevButton) {

    }

    /**
     * 
     */
    public void clickedNext(Button nextButton) {

    }

    /**
     * 
     */
    public void clickedSortByName(Button nameButton) {

    }

    /**
     * 
     */
    public void clickedSortByGenre(Button genreButton) {
        ourList.sortByGenre();
        createFirstGlyphs();
        for (int i = 8; i >= 0; i--)
        {
            if (listPos - i < ourList.getList().size() && listPos - i < glyphs.size())
            {
            String genre = ourList.getList().get(listPos - i).getGenre();
            glyphs.get(listPos - i).setBottomText(genre);
            }
        }
    }

    /**
     * 
     */
    public void clickedSortBySongTitle(Button titleButton) {
        removeGlyphs();
        ourList.sortBySongTitle();
    }

    /**
     * 
     */
    public void clickedSortByReleaseYear(Button yearButton) {

    }

    /**
     * 
     */
    public void clickedRepresentHobby(Button hobbyButton) {
        removeGlyphs();
        createFirstGlyphs();
        mainKey.updateKey("Hobby Legend", "Read", "Art", "Sports", "Music");
        for (int i = 8; i >= 0; i--) {
            if (listPos - i < ourList.getList().size()) {
                int[][] hobbyArray = ourList.getList().get(listPos - i).hobbyArray;
                String title = ourList.getList().get(listPos - i).getSongTitle();
                String author = ourList.getList().get(listPos - i).getArtistName();
                Glyph hobbyGlyph = new Glyph(100 + 200 * ((listPos - i + 3) % 3),
                        100 + 100 * ((listPos - i) / 3), hobbyArray, mainWindow, title,
                        author);
                
                glyphs.add(hobbyGlyph);
            }
        }
        checkButtons();
    }

    /**
     * 
     */
    public void clickedRepresentMajor(Button majorButton) {

    }

    /**
     * 
     */
    public void clickedRepresentRegion(Button regionButton) {

    }

    /**
     * 
     */
    public void clickedQuit(Button quitButton) {

    }

    private void removeGlyphs()
    {
        int amtGlyphs = glyphs.size();
        
        for (int i = 0; i < amtGlyphs; i++)
        {
            int glyphComponents = glyphs.get(0).getGlyphList().size();
            for (int k = 0; k < glyphComponents; k++)
            {
                mainWindow.removeShape(glyphs.get(0).getGlyphList().get(0));
                glyphs.get(0).getGlyphList().remove(0);
            }
            
            glyphs.remove(0);
        }
        
        
    }
    
    private void checkButtons()
    {
        if (listPos == ourList.getList().size() - 1)
        {
            next.disable();
        }
        
        else
        {
            next.enable();
        }
        
        if (listPos == 0)
        {
            previous.disable();
        }
        
        else
        {
            previous.enable();
        }
    }
    
    private void createFirstGlyphs()
    {
        if (listPos == 0)
        {
            listPos = 8;
        }
    }

    /**
     * @author Group54
     * @version 2016.04.11
     */
    private class Key extends Shape {

        /**
         * 
         */
        TextShape keyTitle;

        /**
         * 
         */
        TextShape purpleText;

        /**
         * 
         */
        TextShape blueText;

        /**
         * 
         */
        TextShape yellowText;

        /**
         * 
         */
        TextShape greenText;

        /**
         * 
         */
        TextShape songText;

        /**
         * 
         */
        TextShape heardText;

        /**
         * 
         */
        TextShape likedText;

        /**
         * 
         */
        Shape blackBar;

        /**
         * 
         */
        int xValue;

        /**
         * 
         */
        int yValue;

        /**
         * 
         * @param x
         * @param y
         */
        public Key(int x, int y) {
            super(x, y);
            xValue = x;
            yValue = y;

            // Fields instantiation.
            keyTitle = new TextShape(xValue - 15, yValue, "", Color.BLACK);
            purpleText = new TextShape(xValue, yValue + 25, "", Color.MAGENTA);
            blueText = new TextShape(xValue, yValue + 40, "", Color.BLUE);
            yellowText = new TextShape(xValue, yValue + 55, "", Color.YELLOW);
            greenText = new TextShape(xValue, yValue + 70, "", Color.GREEN);
            songText = new TextShape(xValue - 15, yValue + 90, "Song Title", Color.BLACK);
            heardText = new TextShape(xValue - 29, yValue + 135, "Heard", Color.BLACK);
            likedText = new TextShape(xValue + 23, yValue + 135, "Likes", Color.BLACK);
            blackBar = new Shape(xValue + 13, yValue + 105, 10, 75, Color.BLACK);

            // Fields addition.

            mainWindow.addShape(keyTitle);
            mainWindow.addShape(purpleText);
            mainWindow.addShape(greenText);
            mainWindow.addShape(blueText);
            mainWindow.addShape(yellowText);
            mainWindow.addShape(songText);
            mainWindow.addShape(heardText);
            mainWindow.addShape(likedText);
            mainWindow.addShape(blackBar);
        }

        /**
         * 
         */
        public int getX() {
            return xValue;
        }

        /**
         * 
         */
        public int getY() {
            return yValue;
        }

        /**
         * Is called by the button onClickMethods and redo's the key.
         */
        public void updateKey(String newTitle, String newGreen,
                String newPurple, String newYellow, String newBlue) {
            keyTitle.setText(newTitle);
            greenText.setText(newGreen);
            yellowText.setText(newYellow);
            blueText.setText(newBlue);
            purpleText.setText(newPurple);
        }

    }
}