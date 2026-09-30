import java.util.Arrays;

public class Song {
	
	private String title;
	private String artist;
	private int[] time;
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	public static void main(String[] args){
		System.out.println(new Song("song; mason; 2:3"));

	}
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	public Song(String info){
			String[] split=info.split(INFO_DELIMITER);
			this.title=split[IDX_TITLE];
			this.artist=split[IDX_ARTIST];
			String[] nums=split[IDX_TIME].split(TIME_DELIMITER);
			this.time=new int[nums.length];
			for(int i=0;i<nums.length;i++){
				this.time[nums.length-1-i]=Integer.parseInt(nums[i]);
			}


	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	public String toString(){
		String str=title+"; "+artist+"; ";
		switch(time.length){
			case 1:str+=time[0];
			break;
			case 2:str+=time[1]+":"+String.format("%02d", time[0]);
			break;
			case 3:str+=time[2]+":"+String.format("%02d", time[1])+":"+String.format("%02d", time[0]);
			break;
		}
		return str;
	}
}
