// still need fix

import java.util.*;

public class Map {

  Tile[][] coordinates;
  ArrayList<Tile> tiles = new ArrayList<>();

  public Map(int w, int h) {
    coordinates = new Tile[width][height];
    for (int i = 0; i < w; i++){
      for (int j = 0; j < h; j++){
        coordinates[i][j] = new Tile(i,j);
        tiles.add(coordinates[i][j]);
      }
    }
  }

  // anggapan map berbentuk kotak-kotak array dan player menghadap ke kanan ->

  public void addEntity(Entity e){
    int x = e.getX();
    int y = e.getY();
    coordinates[x][y].addEntity(e);
  }

  public void setTileType(int x, int y, String type){
    coordinates[x][y].setType(type);
  }

  public ArrayList<Tile> getTiles(){
    return new List<>(tiles);
  }

  public ArrayList<Entity> getEntities(){
    List<Entity> entities = new ArrayList<>();
    for(Tile[] row: coordinates){
      for(Tile tile: row){
        entities.addAll(tile.entities);
      }
    }
  }


  public static void main(String[] args) {

  }

}
