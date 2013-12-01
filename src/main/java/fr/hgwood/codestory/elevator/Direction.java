package fr.hgwood.codestory.elevator;

public enum Direction {
    
    UP {
        @Override public Direction reverse() {
            return DOWN;
        }
    },
    
    DOWN {
        @Override public Direction reverse() {
            return UP;
        }
    };
    
    public abstract Direction reverse();

}
