class BeboOLD extends Thread{
	private int ID;
	//Tempo Registrado em cada estado//
	private int timeCasa;
	private int timeBebendo;
	//Tempo Registrado em cada estado//

	//Estado da Thread//
	private int estadoCasa=0;
	private int estadoBebendo=0;
	//Estado da Thread//

	public BeboOLD(int timeCasa, int timeBebendo, int ID){ 	//Construtor
		this.timeCasa = timeCasa;
		this.timeBebendo = timeBebendo;
		this.ID=ID;
	}

	public void run(){
		while(true) {	
		}
	}
	
	public void noBar() throws InterruptedException{
		this.estadoBebendo=1;
		while(this.estadoBebendo==1){
			System.out.printf("Eu %d Estou a beber por %d segundos\n", this.ID,this.timeBebendo);
			super.sleep(this.timeBebendo*10000);
			this.estadoBebendo=0;
		}
	}
	public void saida () throws InterruptedException{
		System.out.printf("%d %d %d\n", this.ID, this.timeCasa, this.timeBebendo);
	}
}
