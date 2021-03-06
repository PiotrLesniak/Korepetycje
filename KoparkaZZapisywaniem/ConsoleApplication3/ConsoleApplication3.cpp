#include "stdafx.h"
#include "Maszyna.h"
#include "Koparka.h"
#include "BazaDanych.h"
#include "Menu.h"
#include <iostream>

using namespace std;


int main()
{

	BazaDanych *bazadanych = new BazaDanych(); //tworzymy obiekt bazy danych
	Menu *menu = new Menu(bazadanych);

	bool dziala = true;
		
	while (dziala)
	{
		switch (menu->UruchomMenu()) //menu->UruchomMenu() - wypisuje opcje menu i pobiera od uzytkownika wybrana opcje
		{
		case 1://odczyt
			menu->Odczytaj();// (plik testowy: test.txt)
			break;
		case 2://zapis
			menu->Zapisz();
			break;
		case 3://dodawanie maszyny
			menu->DodajMaszyne();
			break;
		case 4://usuwanie maszyny
			menu->UsunMaszyne();
			break;
		case 5://wyszukaj maszyne
			menu->Znajdz();
			break;
		case 6:// wypisz wszystkie maszyny
			menu->Wypisz();
			break;
		case 7:dziala = false;
			break;
		}

		

	}

	
	
	return 0;
}

