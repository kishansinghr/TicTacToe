package models;

import enums.CellStatus;
import exceptions.MoveNotAllowedException;

public class Cell {
    private Player player;
    private CellStatus status;

    public Cell() {
        this.player = null;
        this.status  = CellStatus.EMPTY;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) throws MoveNotAllowedException {
        if (this.status == CellStatus.BLOCKED) {
            throw new MoveNotAllowedException("Cell is blocked");
        } else if (this.status == CellStatus.FILLED) {
            throw new MoveNotAllowedException("Cell is already filled");
        } else if (player == null) {
            this.status = CellStatus.EMPTY;
        } else {
            this.status = CellStatus.FILLED;
        }

        this.player = player;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
        if(status==CellStatus.EMPTY) {
            this.player = null;
        }
    }

    public void clear() throws MoveNotAllowedException {
        if(this.status==CellStatus.BLOCKED) {
            throw  new MoveNotAllowedException("Cell is blocked");
        }
        this.setStatus(CellStatus.EMPTY);
    }

    public Symbol getSymbol() {
        if(this.player == null) {
            return null;
        } else {
            return this.getPlayer().getSymbol();
        }
    }
}
