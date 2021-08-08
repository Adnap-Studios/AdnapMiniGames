package com.adnapstudios.adnapminigames.models.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopItem implements Listener {
    private Material material;
    private String displayName;
    private Map<Enchantment, Integer> enchantments;
    private ItemFlag itemFlag;
    private int slot;
    private String lore;
    private int amount = 1;
    private int price;
    private Material currency;
    private boolean isArmor;
    private ArrayList<ItemStack> armor;

    public ShopItem(Material material, String displayName, int slot, String lore) {
        this.material = material;
        this.displayName = displayName;
        this.slot = slot;
        this.lore = lore;
    }

    public ShopItem() {
        this.displayName = "";
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Material getCurrency() {
        return currency;
    }

    public void setCurrency(Material item) {
        this.currency = item;
    }

    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public void addEnchantment(Enchantment enchantment, int level) {
        if (enchantments == null) enchantments = new HashMap<>();
        this.enchantments.put(enchantment, level);
    }

    public ItemFlag getItemFlag() {
        return itemFlag;
    }

    public void setItemFlag(ItemFlag itemFlag) {
        this.itemFlag = itemFlag;
    }

    public boolean isArmor() {
        return isArmor;
    }

    public void setArmor(boolean armor) {
        isArmor = armor;
    }

    public ArrayList<ItemStack> getArmor() {
        return armor;
    }

    public void setArmor(ArrayList<ItemStack> armor) {
        this.armor = armor;
    }

    public void putItemInShop(Inventory shop) {
        shop.setItem(this.slot, getItemStack());
    }

    public ArrayList<ItemStack> getArmorSet() {
        return armor;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (!this.displayName.equals("")) itemMeta.setDisplayName(this.displayName);
        itemStack.setAmount(this.amount);

        if (enchantments != null && enchantments.size() > 0) enchantments.entrySet().forEach(enchantment ->
                itemMeta.addEnchant(enchantment.getKey(), enchantment.getValue(), true));
        if (this.itemFlag != null) itemMeta.addItemFlags(this.itemFlag);

        ArrayList<String> lores = new ArrayList<>();
        if (this.price != 0 && this.currency != null ) {
            lores.add("Shop Item");
            lores.add("Price: " + this.price + " " +  this.currency.name().replace("_"," "));
        }
        itemMeta.setLore(lores);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public ItemStack getItem() {
        ItemStack itemStack = getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta.hasLore()) itemMeta.setLore(new ArrayList<>());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
