import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
//import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//import javax.imageio.stream.FileImageOutputStream;
 
public class Playlist {
	
	private ArrayList<Song> songs;
	
	public Playlist() {
		songs=new ArrayList<Song>();
	}
	public Playlist(String fileName){
		songs=new ArrayList<Song>();
		addSongs(fileName);
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
		if(index<0||index>getNumSongs()||song==null){
			return false;
		}
		songs.add(index, song);
		return true;
	}
	
	public int addSongs(Playlist playlist) {
		
		try{
			int playlistSize=playlist.getNumSongs();
		songs.addAll(Arrays.asList(playlist.getSongs()));
		return playlistSize;}
		catch(NullPointerException e){
			return 0;
		}
		
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.remove(index);
	}
	public void addSongs(String fileName){
		try{
			File file=new File(fileName);
			Scanner scan=new Scanner(file);
			while(scan.hasNextLine()){
				String line=scan.nextLine();
				Song song=new Song(line);
				addSong(song);
			}
			scan.close();
		}
		catch(Exception e){
			System.out.println("Error: "+e.getMessage());
		}
	}
	public String toString(){
		String str="";
		for(Song song:songs){
			str+=song.toString()+System.lineSeparator();
		}
		return str.trim();
	}
	public void saveSongs(String fileName){
		try{
			FileWriter output=new FileWriter(new File(fileName));
			//FileOutputStream output=new FileOutputStream(new File(fileName));
			for(Song song:songs){
				output.write(song.toString());
				output.write(System.lineSeparator());
				}
			output.close();
		}
		catch(Exception e){
			//System.out.println("Error: "+e.getMessage());
		}
	}
	public int[] getTotalTime(){
		int[] totalTime=new int[3];
		for(Song song:songs){
			int[] time=song.getTime();
			for(int i=0;i<time.length;i++){
				totalTime[i]+=time[i];
			}
		}
		if(totalTime[0]>=60){
			totalTime[1]+=totalTime[0]/60;
			totalTime[0]%=60;
		}
		if(totalTime[1]>=60){
			totalTime[2]+=totalTime[1]/60;
			totalTime[1]%=60;
		}
		if(totalTime[2]==0){
			if(totalTime[1]==0){
				return Arrays.copyOf(totalTime, 1);
			}
			else{
				return Arrays.copyOf(totalTime, 2);
			}
			
		}
		
		else{
			return totalTime;
		}

	}

}
