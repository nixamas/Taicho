package enums;

public enum ComponentImages {
	LEVEL_ONE_IMAGE("/Taicho/images/levelOneImage.jpg"), 
	LEVEL_TWO_IMAGE("/Taicho/images/levelTwoImage.jpg"), 
	LEVEL_THREE_IMAGE("/Taicho/images/levelThreeImage.jpg"), 
	TAICHO_IMAGE(""), 
	GAME_BOARD_IMAGE(""), 
	OUT_OF_BOUNDS_IMAGE(""),
	NONE("");
	
	private String location;

	ComponentImages(String loc) {
        this.location = loc;
    }

    public String getImageLocation() {
        return this.location;
    }

}
