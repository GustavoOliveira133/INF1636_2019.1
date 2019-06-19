package regras;
import java.io.IOException;

import gui.*;

class Controlador {
	private int quantidadeJogadores;
	private int vez=-1;
	private int[] jogadoresFalidos;
	private Pino[] pinos;
	private Casa[] casas=new Casa[36];
	
	public Controlador() {
		//Criando as casas (passa o x,y,tipo da casa, seu id e valor
		/* Tipos de casa: 
		 * 0 - Casa inicial
		 * 1 - Terrenos
		 * 2 - Empresa
		 * 3 - Sorte/Reves
		 * 4 - Eventos (pague x)
		 * 5 - Eventos (receba x)
		 * 6 - prisao (vá para bangu I)
		 * 7 - bangu I
		 * 8 - casa cerveja/pagode
		 */
		//Construtor (x,y,tipo,idCasa,Preco) - Construtor Terreno (x,y,tipo,idCasa,Preco,Cor)
		casas[0]=new Casa(20,880,0,0,0); //casa inicial
		casas[1]=new Terreno(15,788,1,1,220,0); //t curicica
		casas[2]=new Casa(15,695,3,2,0);
		casas[3]=new Empresa(15,600,2,3,200);
		casas[4]=new Terreno(15,505,1,4,300,1); //t leme
		casas[5]=new Terreno(15,410,1,5,220,2);//t vilar carioca
		casas[6]=new Casa(15,320,3,6,0);
		casas[7]=new Empresa(15,230,2,7,150);
		casas[8]=new Terreno(15,140,1,8,140,3); //t morro do 18
		casas[9]=new Casa(15,30,7,9,0); //prisao
		casas[10]=new Terreno(130,10,1,10,60,4); //t guapore
		casas[11]=new Terreno(223,10,1,11,180,0); //t tanque
		casas[12]=new Terreno(316,10,1,12,300,1); //t botafogo
		casas[13]=new Casa(409,10,3,13,0);
		casas[14]=new Terreno(502,10,1,14,260,5); //t batan
		casas[15]=new Empresa(595,10,2,15,200);
		casas[16]=new Terreno(688,10,1,16,220,2); //t barbante
		casas[17]=new Casa(781,10,5,17,200); //casa de evento receba x
		casas[18]=new Casa(880,10,8,18,0); //casa cerveja pagode
		casas[19]=new Empresa(870,130,2,19,200);
		casas[20]=new Terreno(870,225,1,20,180,0); //t gardenia azul
		casas[21]=new Terreno(870,318,1,21,140,3); //t caixa dagua
		casas[22]=new Terreno(870,411,1,22,120,5); //t kelsons
		casas[23]=new Casa(870,504,3,23,0);
		casas[24]=new Empresa(870,597,2,24,200);
		casas[25]=new Terreno(870,690,1,25,60,4); //t quitungo
		casas[26]=new Terreno(870,783,1,26,260,1);//t rio das pedras
		casas[27]=new Casa(880,870,6,27,0); //casa va para prisao
		casas[28]=new Terreno(780,875,1,28,160,3); //t fuba
		casas[29]=new Casa(687,875,3,29,0);
		casas[30]=new Terreno(594,875,1,30,240,2); //t carobinha
		casas[31]=new Empresa(501,875,2,31,150);
		casas[32]=new Terreno(408,875,1,32,100,5); //t fumace
		casas[33]=new Casa(315,875,3,33,0);
		casas[34]=new Casa(222,875,4,34,200); //casa de evento pague x
		casas[35]=new Terreno(129,875,1,35,100,4); //t cidade alta
		
		//Coloca multi nas empresas
		((Empresa) casas[3]).setMulti(50); //caca niquel
		((Empresa) casas[7]).setMulti(40); //botijao de gas
		((Empresa) casas[15]).setMulti(50); //transporte alternativo
		((Empresa) casas[19]).setMulti(50); //seguranca
		((Empresa) casas[24]).setMulti(50); //moto-taxi
		((Empresa) casas[31]).setMulti(40); //tv a gato
		
		//coloca os valores nos terrenos (aluguel,1casa,2casas,3casas,4casas,hotel,cadaCasa,cadaHotel)
		((Terreno) casas[1]).setValoresTerreno(28,150,450,1000,1200,1400,200,200); //curicica
		((Terreno) casas[4]).setValoresTerreno(26,130,390,900,1100,1275,200,200); //leme
		((Terreno) casas[5]).setValoresTerreno(18,90,250,700,875,1050,150,150); //vilar carioca
		((Terreno) casas[8]).setValoresTerreno(10,50,150,450,625,750,100,100); //morro do 18
		((Terreno) casas[10]).setValoresTerreno(4,20,60,180,320,450,50,50); //guapore
		((Terreno) casas[11]).setValoresTerreno(14,70,200,550,750,950,100,100); //tanque
		((Terreno) casas[12]).setValoresTerreno(26,130,390,900,1100,1275,200,200);//botafogo
		((Terreno) casas[14]).setValoresTerreno(22,110,330,800,975,1150,150,150);//batan
		((Terreno) casas[16]).setValoresTerreno(18,90,250,700,875,1050,150,150);//barbante
		((Terreno) casas[20]).setValoresTerreno(14,70,200,550,750,950,100,100);//gardenia azul
		((Terreno) casas[21]).setValoresTerreno(10,50,150,450,625,750,100,100);//caixa dagua
		((Terreno) casas[22]).setValoresTerreno(8,40,100,300,450,600,50,50);//kelsons
		((Terreno) casas[25]).setValoresTerreno(4,20,60,180,320,450,50,50);//quitungo
		((Terreno) casas[26]).setValoresTerreno(22,110,330,800,975,1150,150,150);//rio das pedras
		((Terreno) casas[28]).setValoresTerreno(12,60,180,500,700,900,100,100);//fuba
		((Terreno) casas[30]).setValoresTerreno(20,100,300,750,925,1100,150,150);//carobinha
		((Terreno) casas[32]).setValoresTerreno(6,30,90,270,400,500,50,50);//fumace
		((Terreno) casas[35]).setValoresTerreno(6,30,90,270,400,500,50,50);//cidade alta
		
		//coloca imagens terrenos e empresas
		((Terreno) casas[1]).colocaImagem("curicica.jpg");
		((Terreno) casas[4]).colocaImagem("leme.jpg");
		((Terreno) casas[5]).colocaImagem("vilarcarioca.jpg");
		((Terreno) casas[8]).colocaImagem("morro18.jpg");
		((Terreno) casas[10]).colocaImagem("guapore.jpg");
		((Terreno) casas[11]).colocaImagem("tanque.jpg");
		((Terreno) casas[12]).colocaImagem("botafogo.jpg");
		((Terreno) casas[14]).colocaImagem("batan.jpg");
		((Terreno) casas[16]).colocaImagem("barbante.jpg");
		((Terreno) casas[20]).colocaImagem("gardeniaazul.jpg");
		((Terreno) casas[21]).colocaImagem("caixaagua.jpg");
		((Terreno) casas[22]).colocaImagem("kelsons.jpg");
		((Terreno) casas[25]).colocaImagem("quitungo.jpg");
		((Terreno) casas[26]).colocaImagem("riodaspedras.jpg");
		((Terreno) casas[28]).colocaImagem("fuba.jpg");
		((Terreno) casas[30]).colocaImagem("carobinha.jpg");
		((Terreno) casas[32]).colocaImagem("fumace.jpg");
		((Terreno) casas[35]).colocaImagem("cidadealta.jpg");
		((Empresa) casas[3]).colocaImagem("empresa3.jpg"); //caca niquel
		((Empresa) casas[7]).colocaImagem("empresa2.jpg"); //botijao de gas
		((Empresa) casas[15]).colocaImagem("empresa6.jpg"); //transporte alternativo
		((Empresa) casas[19]).colocaImagem("empresa5.jpg"); //seguranca
		((Empresa) casas[24]).colocaImagem("empresa4.jpg"); //moto-taxi
		((Empresa) casas[31]).colocaImagem("empresa1.jpg");//tv a gato
		
		//coloca x e y terreno e empresa
		((Terreno) casas[1]).setXY(8,778,0);
		((Terreno) casas[4]).setXY(8,500,0);
		((Terreno) casas[5]).setXY(8,406,0);
		((Terreno) casas[8]).setXY(8,130,0);
		((Terreno) casas[10]).setXY(130,8,1);
		((Terreno) casas[11]).setXY(222,8,1);
		((Terreno) casas[12]).setXY(314,8,1);
		((Terreno) casas[14]).setXY(500,8,1);
		((Terreno) casas[16]).setXY(686,8,1);
		((Terreno) casas[20]).setXY(872,222,0);
		((Terreno) casas[21]).setXY(872,314,0);
		((Terreno) casas[22]).setXY(872,408,0);
		((Terreno) casas[25]).setXY(872,686,0);
		((Terreno) casas[26]).setXY(872,779,0);
		((Terreno) casas[28]).setXY(779,872,1);
		((Terreno) casas[30]).setXY(585,872,1);
		((Terreno) casas[32]).setXY(408,872,1);
		((Terreno) casas[35]).setXY(129,872,1);
		((Empresa) casas[3]).setXY(8,592,0); //caca niquel
		((Empresa) casas[7]).setXY(8,222,0); //botijao de gas
		((Empresa) casas[15]).setXY(593,8,1);//transporte alternativo
		((Empresa) casas[19]).setXY(872,129,0); //seguranca
		((Empresa) casas[24]).setXY(872,593,0); //moto-taxi
		((Empresa) casas[31]).setXY(502,872,1);//tv a gato
	}
	
	public void setJogadores(int j) {
		vez = 1;
		quantidadeJogadores=j;
		
		jogadoresFalidos = new int[quantidadeJogadores];
		for (int i=0;i<quantidadeJogadores;i++) {
			jogadoresFalidos[i]=0;
		}
		//Criar os pinos de acordo com a quantidade de jogadores selecionado e pinta-los no tabuleiro
		int qtd=quantidadeJogadores;
		pinos=new Pino[qtd];

		if (qtd==2) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
		}
		else if (qtd==3) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
			pinos[2]=new Pino("pin2.png",casas[0],"Laranja",3);
		}
		else if(qtd==4) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
			pinos[2]=new Pino("pin2.png",casas[0],"Laranja",3);
			pinos[3]=new Pino("pin3.png",casas[0],"Amarelo",4);
		}
		else if(qtd==5) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
			pinos[2]=new Pino("pin2.png",casas[0],"Laranja",3);
			pinos[3]=new Pino("pin3.png",casas[0],"Amarelo",4);
			pinos[4]=new Pino("pin4.png",casas[0],"Roxo",5);
		}
			
		else if(qtd==6) {
			pinos[0]=new Pino("pin0.png",casas[0],"Vermelho",1);
			pinos[1]=new Pino("pin1.png",casas[0],"Azul",2);
			pinos[2]=new Pino("pin2.png",casas[0],"Laranja",3);
			pinos[3]=new Pino("pin3.png",casas[0],"Amarelo",4);
			pinos[4]=new Pino("pin4.png",casas[0],"Roxo",5);
			pinos[5]=new Pino("pin5.png",casas[0],"Cinza",6);
		}
	}
	
	public int getJogadores() {
		return quantidadeJogadores;
	}
	public int getVez() {
		return vez;
	}
	public void acabouTurno() {
		vez++;
		if (vez>quantidadeJogadores) {
			vez=1;
		}
		while (jogadoresFalidos[vez-1]==1) {
			vez++;	
			if (vez>quantidadeJogadores) {
				vez=1;
			}
		}
	}
	public void jogadorFaliu(int i) {
		int count=0;
		jogadoresFalidos[i]=1;
		/*
		for (int j=0;j<quantidadeJogadores;j++) {
			count+=jogadoresFalidos[j];
		}
		if (count == (quantidadeJogadores-1)) { //fim de jogo (so restou 1 jogador)
			
		}
		*/
	}
	public int getJogadorFalido (int i) {
		return jogadoresFalidos[i];
	}
	public Pino[] getPinos() {
		return pinos;
	}
	public Casa[] getCasas() {
		return casas;
	}
	public void saveGame() throws IOException {
		Save.guardaInfo(quantidadeJogadores,jogadoresFalidos,vez,pinos,casas);
	}
}

