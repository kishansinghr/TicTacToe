package models;

import java.util.Objects;

public record Symbol(Character character) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals(character, symbol.character);
    }

    @Override
    public String toString() {
        return this.character.toString();
    }
}
