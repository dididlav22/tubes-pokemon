// mungkin harus diubah plyaer dengan entity, jadi entity buat ngumpulin semua termasuk player, pokemon, item, npc

public class Tile {
    int xPos, yPos;
    private String type;
    ArrayList<Entity> entities = new ArrayList<>();

    public Tile(int xPosition, int yPosition) {
        xPos = xPosition;
        yPos = yPosition;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void addEntity(Entity e){
        players.add(e);
    }

    public void removeEntity(Entity e){
        players.remove(e);
    }
}
