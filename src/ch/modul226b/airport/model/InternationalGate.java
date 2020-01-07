package ch.modul226b.airport.model;

public class InternationalGate extends Gate {
  private Size size;

  public InternationalGate (int number, Size size){
    super(number);
    this.size = size;


  }
  public Size getSize(){
    return size;
  }
}
