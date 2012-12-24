package enums;

public enum ComponentImages {
	LEVEL_ONE_IMAGE("images/", "levelOneImage.jpg"), 
	LEVEL_TWO_IMAGE("images/", "levelTwoImage.jpg"), 
	LEVEL_THREE_IMAGE("images/", "levelThreeImage.jpg"), 
	TAICHO_IMAGE("", ""), 
	GAME_BOARD_IMAGE("", ""), 
	OUT_OF_BOUNDS_IMAGE("", ""),
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
