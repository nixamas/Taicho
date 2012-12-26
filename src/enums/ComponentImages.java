package enums;

public enum ComponentImages {
	LEVEL_ONE_IMAGE("images/", "levelOneImage.jpg"), 
	LEVEL_TWO_IMAGE("images/", "levelTwoImage.jpg"), 
	LEVEL_THREE_IMAGE("images/", "levelThreeImage.jpg"), 
	TAICHO_IMAGE("images/", "TaichoImage.png"), 
	GAME_BOARD_IMAGE("images/", ""), 
	OUT_OF_BOUNDS_IMAGE("images/", ""),
	NONE("", "");
	
	private String location;
	private String name;

	ComponentImages(String loc, String n) {
        this.location = loc + n;
        this.name = n;
    }

    public String getImageLocation() {
        return this.location;
    }
    
    public String getImageName() {
    	return this.name;
    }
    
    public String getThumbnailLocation(){
    	return "thumbnails/" + this.name;
    }

}
