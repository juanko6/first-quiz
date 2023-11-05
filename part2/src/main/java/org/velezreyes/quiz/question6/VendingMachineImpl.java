package org.velezreyes.quiz.question6;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    private int money;
    private Map<String, Integer> drinkPrices;
    private Map<String, Drink> availableDrinks;

    public VendingMachineImpl() {
        this.money = 0;
        this.drinkPrices = new HashMap<>();
        this.availableDrinks = new HashMap<>();

        // Inicializa la máquina expendedora con algunas bebidas y sus precios.
        drinkPrices.put("ScottCola", 75);  // ScottCola cuesta 75 centavos.
        drinkPrices.put("KarenTea", 100);  // KarenTea cuesta 100 centavos (1 dólar).

        // Puedes agregar más bebidas aquí.
        availableDrinks.put("ScottCola", new ScottCola());
        availableDrinks.put("KarenTea", new KarenTea());
    }

    public static VendingMachine getInstance() {
        return new VendingMachineImpl();
    }

    @Override
    public void insertQuarter() {
        money += 25;
    }

    @Override
    public Drink pressButton(String name) throws NotEnoughMoneyException, UnknownDrinkException {
        if (availableDrinks.containsKey(name)) {
            int drinkPrice = drinkPrices.get(name);
            if (money >= drinkPrice) {
                money -= drinkPrice;
                return availableDrinks.get(name);
            } else {
                throw new NotEnoughMoneyException();
            }
        } else {
            throw new UnknownDrinkException();
        }
    }

    // Define la implementación de la bebida ScottCola.
    private class ScottCola implements Drink {
        @Override
        public String getName() {
            return "ScottCola";
        }

        @Override
        public boolean isFizzy() {
            return true;
        }
    }

    // Define la implementación de la bebida KarenTea.
    private class KarenTea implements Drink {
        @Override
        public String getName() {
            return "KarenTea";
        }

        @Override
        public boolean isFizzy() {
            return false;
        }
    }
}
