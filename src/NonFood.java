public abstract class NonFood extends Item{
    int length;
    int width;
    int price;
    
    public NonFood(int length,int width, int price){
        super(1);
        this.length = length;
        this.width = width;
        this.price = price;
    }
}
