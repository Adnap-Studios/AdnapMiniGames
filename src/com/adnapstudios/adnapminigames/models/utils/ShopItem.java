package com.adnapstudios.adnapminigames.models.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopItem {
    private Material material;
    private String displayName;
    private Enchantment enchantment;
    private ItemFlag itemFlag;
    private int slot;
    private String lore;
    private int amount = 1;
    private int price;
    private Material currency;

    public ShopItem(Material material, String displayName, int slot, String lore) {
        this.material = material;
        this.displayName = displayName;
        this.slot = slot;
        this.lore = lore;
    }

    public ShopItem() {
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

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public ItemFlag getItemFlag() {
        return itemFlag;
    }

    public void setItemFlag(ItemFlag itemFlag) {
        this.itemFlag = itemFlag;
    }

    public void putItemInShop(Inventory shop) {
        shop.setItem(this.slot, getItemStack());
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(this.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.displayName);
        itemStack.setAmount(this.amount);

        if (this.enchantment != null) itemMeta.addEnchant(this.enchantment, 1, true);
        if (this.itemFlag != null) itemMeta.addItemFlags(this.itemFlag);

        ArrayList<String> lores = new ArrayList<>();
        if (this.price != 0 && this.currency != null )lores.add("Price: " + this.price + " " + this.currency.name().replace("_",""));
        itemMeta.setLore(lores);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
