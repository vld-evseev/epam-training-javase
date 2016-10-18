package training.tasks.unit2.task1;

/**
 * Represents a pen which can change it's color and font weight properties
 * and write some messages to console
 */
public class Pen {

    private Color color = Color.BLACK;
    private FontWeight fontWeight = FontWeight.NORMAL;

    public void write(String string) {
        System.out.println(string);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((fontWeight == null) ? 0 : fontWeight.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pen other = (Pen) obj;
        if (color != other.color)
            return false;
        if (fontWeight != other.fontWeight)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pen [color=" + color + ", fontWeight=" + fontWeight + "]";
    }


}
