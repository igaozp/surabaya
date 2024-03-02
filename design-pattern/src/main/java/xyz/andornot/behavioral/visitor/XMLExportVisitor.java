package xyz.andornot.behavioral.visitor;

public class XMLExportVisitor implements Visitor {
    public String export(Shape... args) {
        var builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
        for (Shape shape : args) {
            builder.append(shape.accept(this)).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String visitDot(Dot dot) {
        return String.format("""
                <dot>
                    <id>%s</id>
                    <x>%s</x>
                    <y>%s</y>
                </dot>
                """, dot.getId(), dot.getX(), dot.getY());
    }

    @Override
    public String visitCircle(Circle circle) {
        return String.format("""
                <circle>
                    <id>%s</id>
                    <x>%s</x>
                    <y>%s</y>
                    <radius>%s</radius>
                </circle>
                """, circle.getId(), circle.getX(), circle.getY(), circle.getRadius());
    }

    @Override
    public String visitRectangle(Rectangle rectangle) {
        return String.format("""
                <rectangle>
                    <id>%s</id>
                    <x>%s</x>
                    <y>%s</y>
                    <width>%s</width>
                    <height>%s</height>
                </rectangle>
                """, rectangle.id(), rectangle.x(), rectangle.y(), rectangle.width(), rectangle.height());
    }

    @Override
    public String visitCompoundGraphic(CompoundShape cg) {
        return String.format("""
                <compound_graphic>
                    <id>%s</id>
                    %s
                </compound_graphic>
                """, cg.getId(), compoundGraphic(cg));
    }

    private String compoundGraphic(CompoundShape cg) {
        var sb = new StringBuilder();
        for (Shape shape : cg.children) {
            String obj = shape.accept(this);
            obj = "    " + obj.replace("\n", "\n    ") + "\n";
            sb.append(obj);
        }
        return sb.toString();
    }
}
