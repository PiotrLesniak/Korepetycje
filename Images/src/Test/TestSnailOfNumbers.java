package Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import Algoritms.SnailOfNumbers;
import Logic.INumbersArrea;
import Logic.TestNumbersArrea;

public class TestSnailOfNumbers {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);//inicjujemy nasze mocki 
		//czyli do interfejsow oznaczonych adnotacja @Mock tworzymy odpowiednie obiekty (takie na niby) nazywane mockami
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Mock //ta adnotacja pokazuje bibliotece Mockito ze ta referencje trzeba zamokowac
	INumbersArrea numbersArrea;
	
	@Test
	public void testAlgoritm() {
		//na potrzeby testu tworzymy klase testowa dla przestrzeni liczb.
		//algorytm ukladnaia liczb w spirali bedzie tam dawal liczby w okreslonym ulorzeniu
		TestNumbersArrea t = new TestNumbersArrea();
		
		SnailOfNumbers snailOfNumbers = new SnailOfNumbers(t);
		//podajemy do alorytmu nasza klase testowa.
		//mozemy to zrobic bo algorytm przyjmuje kazda klase inplementujaca interfejs 'INumbersArrea'
		
		snailOfNumbers.runAlgoritm();// uruamiamy ukladanie luczb w spirale

		//tworzymy tablice i wpisujemy do niej efekt ktorego oczekujemy
		//jest to poprawny wynik dzialania klasy SnailOfNumbers
		long[][] expected = new long[][] { 
			    { 101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91 },
				{ 102, 65, 64, 63, 62, 61, 60, 59, 58, 57, 90 }, 
				{ 103, 66, 37, 36, 35, 34, 33, 32, 31, 56, 89 },
				{ 104, 67, 38, 17, 16, 15, 14, 13, 30, 55, 88 }, 
				{ 105, 68, 39, 18, 5, 4, 3, 12, 29, 54, 87 },
				{ 106, 69, 40, 19, 6, 1, 2, 11, 28, 53, 86 },
				{ 107, 70, 41, 20, 7, 8, 9, 10, 27, 52, 85 },
				{ 108, 71, 42, 21, 22, 23, 24, 25, 26, 51, 84 }, 
				{ 109, 72, 43, 44, 45, 46, 47, 48, 49, 50, 83 },
				{ 110, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82 },
				{ 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121 } };
				
				//powurnjemy nasz oczekiwany efekt z efektem dzialania algorytmu
				Assert.assertArrayEquals(expected, t.GetArray()); 
				//w zaleznosci czy wynik dzialania algorytmy bedzie identyczy z oczekiwanym czy nie 
				//to test zapali sie na odpowiedni kolor.
				
				
				//Uruchamianie testow
				//Klikamy prawym na klase z testami, lub na calym pakiecie z testami
				//wybieramy z menu run us JUnit test
				
				//https://kobietydokodu.pl/niezbednik-juniora-mockito/
	}

	//algorytm z uzyciem mockow
	@Test
	public void testAlgoritm2() {
		
		//numbersArrea pod tym interfejsem kryje sie obiekt wygenerowany przez Mockito
		SnailOfNumbers snailOfNumbers = new SnailOfNumbers(numbersArrea);//tworzymy obiekt algorytmu podajac wygenerowany obiekt
		
		//programujemy nasz obiekt
		Mockito.when(numbersArrea.getSize()).thenReturn(3);//getSize() zawsze zwroci 3
		Mockito.doNothing().when(numbersArrea).setNumber(Matchers.anyObject(), Matchers.anyObject(), Matchers.anyObject());//setNumber nic nie bedzie zwracac
		
		snailOfNumbers.runAlgoritm();// uruamiamy nasz algorytm

		Mockito.verify(numbersArrea, Mockito.times(4)).getSize();//weryfikujemy czy algorytm uruchomi� getSize() 4 razy  
		
		//weryfikujemy czy algorytm uruchomi� setNumber 9 razy
		Mockito.verify(numbersArrea, Mockito.times(9)).setNumber(Matchers.anyObject(), Matchers.anyObject(), Matchers.anyObject());
		
		//https://kobietydokodu.pl/niezbednik-juniora-mockito/
	}

	
}
