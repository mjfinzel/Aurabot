package Program;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ItemInfo {
	public static Item item;

	public static final int CURRENCY = 0;
	public static final int NORMAL = 1;
	public static final int MAGIC = 2;
	public static final int RARE = 3;
	public static final int UNIQUE = 4;
	public static final int GEM = 5;
	public static final int CARD = 16;

	public static final int GLOVES = 6;
	public static final int BOOTS = 7;
	public static final int HELMET = 8;
	public static final int BODY_ARMOUR = 9;
	public static final int RING = 10;
	public static final int AMULET = 11;
	public static final int SHIELD = 12;
	public static final int TWO_HAND = 13;
	public static final int ONE_HAND = 14;
	public static final int QUIVER = 15;
	public static final int BELT = 17;
	public static final int FLASK = 18;
	public static final int FISHING_ROD = 19;

	public static final int BOW = 20;
	public static final int STAFF = 21;
	public static final int TWO_HAND_SWORD = 22;
	public static final int TWO_HAND_AXE = 23;
	public static final int TWO_HAND_MACE = 24;
	public static final int CLAW = 25;
	public static final int DAGGER = 26;
	public static final int WAND = 27;
	public static final int ONE_HAND_SWORD = 28;
	public static final int THRUSTING_SWORD = 29;
	public static final int ONE_HAND_AXE = 30;
	public static final int ONE_HAND_MACE = 31;
	public static final int SCEPTRE = 32;

	public static String[] names = {"Currency", "Normal", "Magic", "Rare", "Unique", "Gem", "Gloves", "Boots", "Helmet", "Body Armour", "Ring", "Amulet", "Shield", "Two Handed Weapon", "One Handed Weapon", "Quiver", "Divination Card", "Belt", "Flask", "Fishing Rod", "Bow", "Staff", "Two-Handed Sword", "Two-Handed Axe", "Two-Handed Mace", "Claw", "Dagger", "Wand", "One-Handed Sword", "Thrusting Sword", "One-Handed Axe", "One-Handed Mace", "Sceptre"};

	public static String[] gloveBases = {"Iron Gauntlets", "Plated Gauntlets", "Bronze Gauntlets", "Steel Gauntlets", "Antique Gauntlets", "Ancient Gauntlets", "Goliath Gauntlets", "Vaal Gauntlets", "Titan Gauntlets", "Rawhide Gloves", "Goathide Gloves", "Deerskin Gloves", "Nubuck Gloves", "Eelskin Gloves", "Sharkskin Gloves", "Shagreen Gloves", "Stealth Gloves", "Slink Gloves", "Wool Gloves", "Velvet Gloves", "Silk Gloves", "Embroidered Gloves", "Samite Gloves", "Conjurer Gloves", "Arcanist Gloves", "Sorcerer Gloves", "Fishscale Gauntlets", "Ironscale Gauntlets", "Bronzescale Gauntlets", "Steelscale Gauntlets", "Serpentscale Gauntlets", "Wyrmscale Gauntlets", "Hydrascale Gauntlets", "Dragonscale Gauntlets", "Chain Gloves", "Ringmail Gloves", "Mesh Gloves", "Riveted Gloves", "Zealot Gloves", "Soldier Gloves", "Legion Gloves", "Crusader Gloves", "Wrapped Mitts", "Strapped Mitts", "Clasped Mitts", "Trapper Mitts", "Ambush Mitts", "Carnal Mitts", "Assassin's Mitts", "Murder Mitts", "Golden Bracers", "Spiked Gloves", "Gripped Gloves", "Fingerless Silk Gloves"};
	public static String[] bootBases = {"Iron Greaves", "Steel Greaves", "Plated Greaves", "Reinforced Greaves", "Antique Greaves", "Ancient Greaves", "Goliath Greaves", "Vaal Greaves", "Titan Greaves", "Kaom's Greaves", "Rawhide Boots", "Goathide Boots", "Deerskin Boots", "Nubuck Boots", "Eelskin Boots", "Sharkskin Boots", "Shagreen Boots", "Stealth Boots", "Slink Boots", "Wool Shoes", "Velvet Slippers", "Silk Slippers", "Scholar Boots", "Satin Slippers", "Samite Slippers", "Conjurer Boots", "Arcanist Slippers", "Sorcerer Boots", "Leatherscale Boots", "Ironscale Boots", "Bronzescale Boots", "Steelscale Boots", "Serpentscale Boots", "Wyrmscale Boots", "Hydrascale Boots", "Dragonscale Boots", "Chain Boots", "Ringmail Boots", "Mesh Boots", "Riveted Boots", "Zealot Boots", "Soldier Boots", "Legion Boots", "Crusader Boots", "Wrapped Boots", "Strapped Boots", "Clasped Boots", "Shackled Boots", "Trapper Boots", "Ambush Boots", "Carnal Boots", "Assassin's Boots", "Murder Boots", "Golden Caligae", "Avian Slippers", "Two-Toned Boots"};
	public static String[] chestBases = {"Plate Vest", "Chestplate", "Copper Plate", "War Plate", "Full Plate", "Arena Plate", "Lordly Plate", "Bronze Plate", "Battle Plate", "Sun Plate", "Colosseum Plate", "Majestic Plate", "Golden Plate", "Crusader Plate", "Astral Plate", "Gladiator Plate", "Glorious Plate", "Kaom's Plate", "Shabby Jerkin", "Strapped Leather", "Buckskin Tunic", "Wild Leather", "Full Leather", "Sun Leather", "Thief's Garb", "Eelskin Tunic", "Frontier Leather", "Glorious Leather", "Coronal Leather", "Cutthroat's Garb", "Sharkskin Tunic", "Destiny Leather", "Exquisite Leather", "Zodiac Leather", "Assassin's Garb", "Simple Robe", "Silken Vest", "Scholar's Robe", "Silken Garb", "Mage's Vestment", "Silk Robe", "Cabalist Regalia", "Sage's Robe", "Silken Wrap", "Conjurer's Vestment", "Spidersilk Robe", "Destroyer Regalia", "Savant's Robe", "Necromancer Silks", "Occultist's Vestment", "Widowsilk Robe", "Vaal Regalia", "Scale Vest", "Light Brigandine", "Scale Doublet", "Infantry Brigandine", "Full Scale Armour", "Soldier's Brigandine", "Field Lamellar", "Wyrmscale Doublet", "Hussar Brigandine", "Full Wyrmscale", "Commander's Brigandine", "Battle Lamellar", "Dragonscale Doublet", "Desert Brigandine", "Full Dragonscale", "General's Brigandine", "Triumphant Lamellar", "Chainmail Vest", "Chainmail Tunic", "Ringmail Coat", "Chainmail Doublet", "Full Ringmail", "Full Chainmail", "Holy Chainmail", "Latticed Ringmail", "Crusader Chainmail", "Ornate Ringmail", "Chain Hauberk", "Devout Chainmail", "Loricated Ringmail", "Conquest Chainmail", "Elegant Ringmail", "Saint's Hauberk", "Saintly Chainmail", "Padded Vest", "Oiled Vest", "Padded Jacket", "Oiled Coat", "Scarlet Raiment", "Waxed Garb", "Bone Armour", "Quilted Jacket", "Sleek Coat", "Crimson Raiment", "Lacquered Garb", "Crypt Armour", "Sentinel Jacket", "Varnished Coat", "Blood Raiment", "Sadist Garb", "Carnal Armour", "Sacrificial Garb", "Golden Mantle"};
	public static String[] helmetBases = {"Cone Helmet", "Barbute Helmet", "Close Helmet", "Gladiator Helmet", "Reaver Helmet", "Siege Helmet", "Samite Helmet", "Ezomyte Burgonet", "Royal Burgonet", "Eternal Burgonet", "Leather Cap", "Tricorne", "Leather Hood", "Wolf Pelt", "Hunter Hood", "Noble Tricorne", "Ursine Pelt", "Silken Hood", "Sinner Tricorne", "Lion Pelt", "Vine Circlet", "Iron Circlet", "Torture Cage", "Tribal Circlet", "Bone Circlet", "Lunaris Circlet", "Steel Circlet", "Necromancer Circlet", "Solaris Circlet", "Mind Cage", "Hubris Circlet", "Battered Helm", "Sallet", "Visored Sallet", "Gilded Sallet", "Secutor Helm", "Fencer Helm", "Lacquered Helmet", "Fluted Bascinet", "Pig-Faced Bascinet", "Nightmare Bascinet", "Rusted Coif", "Soldier Helmet", "Great Helmet", "Crusader Helmet", "Aventail Helmet", "Zealot Helmet", "Great Crown", "Magistrate Crown", "Prophet Crown", "Praetor Crown", "Scare Mask", "Plague Mask", "Iron Mask", "Festival Mask", "Golden Mask", "Raven Mask", "Callous Mask", "Regicide Mask", "Harlequin Mask", "Vaal Mask", "Deicide Mask", "Golden Wreath", "Bone Helmet"};
	public static String[] amuletBases = {"Coral Amulet", "Amber Amulet", "Jade Amulet", "Lapis Amulet", "Gold Amulet", "Onyx Amulet", "Turquoise Amulet", "Agate Amulet", "Citrine Amulet", "Ruby Amulet", "Jet Amulet", "Blue Pearl Amulet", "Marble Amulet", "Black Maw Talisman", "Bonespire Talisman", "Ashscale Talisman", "Lone Antler Talisman", "Deep One Talisman", "Breakrib Talisman", "Deadhand Talisman", "Undying Flesh Talisman", "Rot Head Talisman", "Mandible Talisman", "Chrysalis Talisman", "Writhing Talisman", "Hexclaw Talisman", "Primal Skull Talisman", "Wereclaw Talisman", "Splitnewt Talisman", "Clutching Talisman", "Avian Twins Talisman", "Avian Twins Talisman", "Avian Twins Talisman", "Avian Twins Talisman", "Avian Twins Talisman", "Avian Twins Talisman", "Fangjaw Talisman", "Horned Talisman", "Spinefuse Talisman", "Three Rat Talisman", "Monkey Twins Talisman", "Longtooth Talisman", "Rotfeather Talisman", "Monkey Paw Talisman", "Monkey Paw Talisman", "Monkey Paw Talisman", "Three Hands Talisman", "Greatwolf Talisman"};
	public static String[] ringBases = {"Golden Hoop", "Iron Ring", "Coral Ring", "Paua Ring", "Gold Ring", "Topaz Ring", "Sapphire Ring", "Ruby Ring", "Prismatic Ring", "Moonstone Ring", "Amethyst Ring", "Diamond Ring", "Two-Stone Ring", "Two-Stone Ring", "Two-Stone Ring", "Unset Ring", "Jet Ring", "Steel Ring", "Opal Ring", "Breach Ring"};
	public static String[] beltBases = {"Rustic Sash", "Chain Belt", "Leather Belt", "Heavy Belt", "Cloth Belt", "Studded Belt", "Vanguard Belt", "Crystal Belt", "Golden Obi", "Stygian Vise"};
	public static String[] shieldBases = {"Splintered Tower Shield", "Corroded Tower Shield", "Rawhide Tower Shield", "Cedar Tower Shield", "Copper Tower Shield", "Reinforced Tower Shield", "Painted Tower Shield", "Buckskin Tower Shield", "Mahogany Tower Shield", "Bronze Tower Shield", "Girded Tower Shield", "Crested Tower Shield", "Shagreen Tower Shield", "Ebony Tower Shield", "Ezomyte Tower Shield", "Colossal Tower Shield", "Pinnacle Tower Shield", "Goathide Buckler", "Pine Buckler", "Painted Buckler", "Hammered Buckler", "War Buckler", "Gilded Buckler", "Oak Buckler", "Enameled Buckler", "Corrugated Buckler", "Battle Buckler", "Golden Buckler", "Ironwood Buckler", "Lacquered Buckler", "Vaal Buckler", "Crusader Buckler", "Imperial Buckler", "Twig Spirit Shield", "Yew Spirit Shield", "Bone Spirit Shield", "Tarnished Spirit Shield", "Jingling Spirit Shield", "Brass Spirit Shield", "Walnut Spirit Shield", "Ivory Spirit Shield", "Ancient Spirit Shield", "Chiming Spirit Shield", "Thorium Spirit Shield", "Lacewood Spirit Shield", "Fossilised Spirit Shield", "Vaal Spirit Shield", "Harmonic Spirit Shield", "Titanium Spirit Shield", "Rotted Round Shield", "Fir Round Shield", "Studded Round Shield", "Scarlet Round Shield", "Splendid Round Shield", "Maple Round Shield", "Spiked Round Shield", "Crimson Round Shield", "Baroque Round Shield", "Teak Round Shield", "Spiny Round Shield", "Cardinal Round Shield", "Elegant Round Shield", "Plank Kite Shield", "Linden Kite Shield", "Reinforced Kite Shield", "Layered Kite Shield", "Ceremonial Kite Shield", "Etched Kite Shield", "Steel Kite Shield", "Laminated Kite Shield", "Angelic Kite Shield", "Branded Kite Shield", "Champion Kite Shield", "Mosaic Kite Shield", "Archon Kite Shield", "Spiked Bundle", "Driftwood Spiked Shield", "Alloyed Spiked Shield", "Burnished Spiked Shield", "Ornate Spiked Shield", "Redwood Spiked Shield", "Compound Spiked Shield", "Polished Spiked Shield", "Sovereign Spiked Shield", "Alder Spiked Shield", "Ezomyte Spiked Shield", "Mirrored Spiked Shield", "Supreme Spiked Shield", "Golden Flame"};

	public static String[] bowBases = {"Crude Bow", "Short Bow", "Long Bow", "Composite Bow", "Recurve Bow", "Bone Bow", "Royal Bow", "Death Bow", "Grove Bow", "Reflex Bow", "Decurve Bow", "Compound Bow", "Sniper Bow", "Ivory Bow", "Highborn Bow", "Decimation Bow", "Thicket Bow", "Steelwood Bow", "Citadel Bow", "Ranger Bow", "Assassin Bow", "Spine Bow", "Imperial Bow", "Harbinger Bow", "Maraketh Bow",};
	public static String[] staffBases = {"Gnarled Branch", "Primitive Staff", "Long Staff", "Iron Staff", "Coiled Staff", "Royal Staff", "Vile Staff", "Crescent Staff", "Woodful Staff", "Quarterstaff", "Military Staff", "Serpentine Staff", "Highborn Staff", "Foul Staff", "Moon Staff", "Primordial Staff", "Lathi", "Ezomyte Staff", "Maelström Staff", "Imperial Staff", "Judgement Staff", "Eclipse Staff",};
	public static String[] twoHandSwordBases = {"Keyblade", "Corroded Blade", "Longsword", "Bastard Sword", "Two-Handed Sword", "Etched Greatsword", "Ornate Sword", "Spectral Sword", "Curved Blade", "Butcher Sword", "Footman Sword", "Highland Blade", "Engraved Greatsword", "Tiger Sword", "Wraith Sword", "Lithe Blade", "Headman's Sword", "Reaver Sword", "Ezomyte Blade", "Vaal Greatsword", "Lion Sword", "Infernal Sword", "Exquisite Blade",};
	public static String[] twoHandAxeBases = {"Stone Axe", "Jade Chopper", "Woodsplitter", "Poleaxe", "Double Axe", "Gilded Axe", "Shadow Axe", "Dagger Axe", "Jasper Chopper", "Timber Axe", "Headsman Axe", "Labrys", "Noble Axe", "Abyssal Axe", "Karui Chopper", "Talon Axe", "Sundering Axe", "Ezomyte Axe", "Vaal Axe", "Despot Axe", "Void Axe", "Fleshripper",};
	public static String[] twoHandMaceBases = {"Driftwood Maul", "Tribal Maul", "Mallet", "Sledgehammer", "Jagged Maul", "Brass Maul", "Fright Maul", "Morning Star", "Totemic Maul", "Great Mallet", "Steelhead", "Spiny Maul", "Plated Maul", "Dread Maul", "Solar Maul", "Karui Maul", "Colossus Mallet", "Piledriver", "Meatgrinder", "Imperial Maul", "Terror Maul", "Coronal Maul"};

	public static String[] clawBases = {"Nailed Fist", "Sharktooth Claw", "Awl", "Cat's Paw", "Blinder", "Timeworn Claw", "Sparkling Claw", "Fright Claw", "Double Claw", "Thresher Claw", "Gouger", "Tiger's Paw", "Gut Ripper", "Prehistoric Claw", "Noble Claw", "Eagle Claw", "Twin Claw", "Great White Claw", "Throat Stabber", "Hellion's Paw", "Eye Gouger", "Vaal Claw", "Imperial Claw", "Terror Claw", "Gemini Claw",};
	public static String[] daggerBases = {"Skinning Knife", "Carving Knife", "Boot Knife", "Copper Kris", "Skean", "Imp Dagger", "Prong Dagger", "Flaying Knife", "Butcher Knife", "Poignard", "Boot Blade", "Golden Kris", "Royal Skean", "Fiend Dagger", "Trisula", "Gutting Knife", "Slaughter Knife", "Ambusher", "Ezomyte Dagger", "Platinum Kris", "Imperial Skean", "Demon Dagger", "Sai",};
	public static String[] wandBases = {"Driftwood Wand", "Goat's Horn", "Carved Wand", "Quartz Wand", "Spiraled Wand", "Sage Wand", "Pagan Wand", "Faun's Horn", "Engraved Wand", "Crystal Wand", "Serpent Wand", "Omen Wand", "Heathen Wand", "Demon's Horn", "Imbued Wand", "Opal Wand", "Tornado Wand", "Prophecy Wand", "Profane Wand",};
	public static String[] oneHandSwordBases = {"Charan's Sword", "Rusted Sword", "Copper Sword", "Sabre", "Broad Sword", "War Sword", "Ancient Sword", "Elegant Sword", "Dusk Blade", "Hook Sword", "Variscite Blade", "Cutlass", "Baselard", "Battle Sword", "Elder Sword", "Graceful Sword", "Twilight Blade", "Grappler", "Gemstone Sword", "Corsair Sword", "Gladius", "Legion Sword", "Vaal Blade", "Eternal Sword", "Midnight Blade", "Tiger Hook", };
	public static String[] thrustingSwordBases = {"Rusted Spike", "Whalebone Rapier", "Battered Foil", "Basket Rapier", "Jagged Foil", "Antique Rapier", "Elegant Foil", "Thorn Rapier", "Smallsword", "Wyrmbone Rapier", "Burnished Foil", "Estoc", "Serrated Foil", "Primeval Rapier", "Fancy Foil", "Apex Rapier", "Courtesan Sword", "Dragonbone Rapier", "Tempered Foil", "Pecoraro", "Spiraled Foil", "Vaal Rapier", "Jewelled Foil", "Harpy Rapier", "Dragoon Sword",};
	public static String[] oneHandAxeBases = {"Rusted Hatchet", "Jade Hatchet", "Boarding Axe", "Cleaver", "Broad Axe", "Arming Axe", "Decorative Axe", "Spectral Axe", "Etched Hatchet", "Jasper Axe", "Tomahawk", "Wrist Chopper", "War Axe", "Chest Splitter", "Ceremonial Axe", "Wraith Axe", "Engraved Hatchet", "Karui Axe", "Siege Axe", "Reaver Axe", "Butcher Axe", "Vaal Hatchet", "Royal Axe", "Infernal Axe", "Runic Hatchet",};
	public static String[] oneHandMaceBases = {"Driftwood Club", "Tribal Club", "Spiked Club", "Stone Hammer", "War Hammer", "Bladed Mace", "Ceremonial Mace", "Dream Mace", "Wyrm Mace", "Petrified Club", "Barbed Club", "Rock Breaker", "Battle Hammer", "Flanged Mace", "Ornate Mace", "Phantom Mace", "Dragon Mace", "Ancestral Club", "Tenderizer", "Gavel", "Legion Hammer", "Pernarch", "Auric Mace", "Nightmare Mace", "Behemoth Mace",};
	public static String[] sceptreBases = {"Driftwood Sceptre", "Darkwood Sceptre", "Bronze Sceptre", "Quartz Sceptre", "Iron Sceptre", "Ochre Sceptre", "Ritual Sceptre", "Shadow Sceptre", "Horned Sceptre", "Grinning Fetish", "Sekhem", "Crystal Sceptre", "Lead Sceptre", "Blood Sceptre", "Royal Sceptre", "Abyssal Sceptre", "Stag Sceptre", "Karui Sceptre", "Tyrant's Sekhem", "Opal Sceptre", "Platinum Sceptre", "Vaal Sceptre", "Carnal Sceptre", "Void Sceptre", "Sambar Sceptre"};

	public static String[] bowSuffixes = {};
	public static String[] bowPrefixes = {};
	public static String[] staffSuffixes = {};
	public static String[] staffPrefixes = {};
	public static String[] twoHandMeleeSuffixes = {"to Strength", " to Dexterity", "% increased Attack Speed", "% reduced Enemy Stun Threshold", "% to Fire Resistance", "% to Cold Resistance", "% to Lightning Resistance", "% to Chaos Resistance", "% increased Stun Duration on Enemies", " Life gained for each Enemy hit by Attacks", " Life gained on Kill", " Mana gained on Kill", "% increased Critical Strike Chance", "% to Global Critical Strike Multiplier", " to Accuracy Rating", "% reduced Attribute Requirements", "% increased Light Radius", "% increased Poison Duration", "% increased Bleeding Duration", "% chance to cause Bleeding on Hit", "% chance to Poison on Hit", "% increased Damage with Poison", "% increased Damage with Bleeding", " to Accuracy Rating", " Life gained for each Enemy hit by your Attacks"};
	public static String[] twoHandMeleePrefixes = {"% increased Physical Damage", " to Accuracy Rating", "% increased Physical Damage", " to Physical Damage", " to Fire Damage", " to Cold Damage", " to Lightning Damage", "% increased Elemental Damage with Attack Skills", " to Level of Socketed Gems", " to Level of Socketed Melee Gems", "% of Physical Attack Damage Leeched as Life", "% of Physical Attack Damage Leeched as Mana", " to Chaos Damage", "% chance to cause Bleeding on Hit", "% reduced Enemy Block Chance", "% increased Damage over Time"};

	public static String getClipboardData() {
		String data;
		try {
			data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
			return data;
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		return null;
	}
	public static int getRarity(String s) {
		if(s.contains("Currency")) {
			return CURRENCY;
		}
		if(s.contains("Normal")) {
			return NORMAL;
		}
		if(s.contains("Magic")) {
			return MAGIC;
		}
		if(s.contains("Rare")) {
			return RARE;
		}
		if(s.contains("Unique")) {
			return UNIQUE;
		}
		if(s.contains("Gem")) {
			return GEM;
		}
		if(s.contains("Divination Card")) {
			return CARD;
		}
		return -1;
	}
	public static void determineAffixes(String[] lines) {
		if(item.rarity!=RARE) return;
		//determine item affixes
		boolean foundILVL = false;
		int firstBar=-1;
		int start = 0;
		for(int i = 0; i<lines.length;i++) {
			if(lines[i].contains("Energy Shield: ")) {
				int end = lines[i].length();
				if(lines[i].contains("augmented")) end = lines[i].length()-" (augmented)".length();
				item.energyShield = Integer.valueOf(lines[i].substring("Energy Shield: ".length(), end));
			}
			if(!foundILVL&&lines[i].contains("Item Level:")) foundILVL=true;
			if(foundILVL&&lines[i].contains("----")||i==lines.length-1) {
				if(firstBar==-1) {
					firstBar=i;
				}
				else {
					if(i-firstBar>2) {
						start = firstBar+1;
						break;
					}
					else
						firstBar=i;
				}
			}
		}
		if(!foundILVL) System.out.println("FAILED TO FIND ILVL");
		for(int i = start; i<lines.length&&!lines[i].contains("---");i++) {
			item.affixes.add(lines[i]);
			if(lines[i].contains("% increased Movement Speed")) {
				item.movespeed = Integer.valueOf(lines[i].substring(0, lines[i].length()-"% increased Movement Speed".length()));
			}
			if(lines[i].contains("% increased Spell Damage")) {
				item.spellDmg = Integer.valueOf(lines[i].substring(0, lines[i].length()-"% increased Spell Damage".length()));
			}
			if(lines[i].contains("% increased Rarity of Items found")) {
				item.increasedRarity = Integer.valueOf(lines[i].substring(0, lines[i].length()-"% increased Rarity of Items found".length()));
			}
			if(lines[i].contains("to Maximum Life")) {
				item.life = Integer.valueOf(lines[i].substring(1, lines[i].length()-" to Maximum Life".length()));
			}
			if(lines[i].contains("to Accuracy Rating")) {
				item.accuracy = Integer.valueOf(lines[i].substring(1, lines[i].length()-" to Accuracy Rating".length()));
			}
		}
		if(item.baseType==TWO_HAND_SWORD||item.baseType==TWO_HAND_AXE||item.baseType==TWO_HAND_MACE||item.baseType==BOW) {
			for(int i = start; i<lines.length&& !lines[i].contains("---");i++) {
				
				if(lines[i].contains("% increased Light Radius")) {
					item.lightRadius = Integer.valueOf(lines[i].substring(0, lines[i].length()-"% increased Light Radius".length()));
				}
				for(int j = 0; j<twoHandMeleePrefixes.length;j++) {
					if(lines[i].contains(twoHandMeleePrefixes[j])) {
						item.prefixes.add(lines[i]);
					}
				}
				for(int j = 0; j<twoHandMeleeSuffixes.length;j++) {
					if(lines[i].contains(twoHandMeleeSuffixes[j])) {
						item.suffixes.add(lines[i]);
					}
				}
				if(lines[i].contains(" Bow Gems")) {
					item.prefixes.add(lines[i]);
				}
			}
		}
		if(item.baseType==BOOTS) {
			
		}
		
	}

	public static void getDPS(String[] lines) {

		determineAffixes(lines);
		boolean foundStart=false;
		for(int i = 0; i<lines.length;i++) {
			if(lines[i].contains("--------")) {
				if(foundStart) break;
				foundStart=true;
			}
			else if(foundStart) {
				int end = lines[i].length();
				if(lines[i].contains("augmented")) end = lines[i].length()-" (augmented)".length();
				if(lines[i].contains("Physical Damage")) {
					String[] range = lines[i].substring("Physical Damage: ".length(), end).split("-");
					item.physDmg=(Double.valueOf(range[0])+Double.valueOf(range[1]))/2;	
				}
				if(lines[i].contains("Chaos Damage")) {
					String[] range = lines[i].substring("Chaos Damage: ".length(), end).split("-");
					item.chaosDmg=(Double.valueOf(range[0])+Double.valueOf(range[1]))/2;	
				}
				if(lines[i].contains("Elemental Damage")) {
					String[] rolls = lines[i].substring("Elemental Damage: ".length(), end).split(",");
					for(int j = 0; j<rolls.length;j++) {
						String[] range = rolls[j].split("-");
						if(range[1].contains("augmented")) range[1] = range[1].substring(0, range[1].length()-" (augmented)".length());
						item.eleDmg+=(Double.valueOf(range[0])+Double.valueOf(range[1]))/2;	
					}
				}
				if(lines[i].contains("Attacks per Second")) {
					item.attackSpeed=Double.valueOf(lines[i].substring("Attacks per Second: ".length(), end));		
				}
			}
		}

		//factor in quality and determine the increased physical damage roll on the weapon
		double increasedPhys = 0;
		double increasedAtkSpd = 0;
		for(int i = 0; i<lines.length;i++) {
			if(lines[i].contains("Quality: ")) {
				item.quality = Integer.valueOf(lines[i].substring(10, lines[i].length()-13));
			}
			if(lines[i].contains("% increased Physical Damage")) {
				increasedPhys = Integer.valueOf(lines[i].substring(0, lines[i].length()-"% Increased Physical Damage".length()));
			}
			if(lines[i].contains("% increased Attack Speed")) {
				increasedAtkSpd = Integer.valueOf(lines[i].substring(0, lines[i].length()-"% Increased Attack Speed".length()));
			}
		}
		double basePhysDmg = item.physDmg/((increasedPhys+item.quality+100.0)/100.0);
		
		//check if the item can be master crafted
		if((item.prefixes.size()<=3&&item.accuracy>0&&item.lightRadius==0)||item.prefixes.size()<=2) {
			if(increasedPhys>50&&item.hasPrefix("Adds ", "Physical")==null) {
				basePhysDmg+=22;
				item.crafted = "Adds 16-28 Physical Damage";
			}
			else if(increasedPhys==0||(increasedPhys>0&&increasedPhys<=79&&item.accuracy>0&&item.accuracy<=169&&item.lightRadius==0)) {
				increasedPhys+=70;
				item.crafted = "70% increased Physical Damage";
			}
		}
		else {
			if((item.suffixes.size()<=3&&item.accuracy>0)||item.suffixes.size()<=2) {
				if(item.hasSuffix("Attack Speed")==null) {
					item.attackSpeed=item.attackSpeed*1.14;
					item.crafted = "14% increased Attack Speed";
				}
			}
		}
		

		item.physDmg=basePhysDmg*((increasedPhys+120.0)/100);
		item.dps=(item.physDmg+item.eleDmg+item.chaosDmg)*item.attackSpeed;
		item.pdps=item.physDmg*item.attackSpeed;
		item.edps=item.eleDmg*item.attackSpeed;
	}
	public static void getSockets(String[] lines) {
		int currentLinks = 1;
		int maxLinks = 1;
		for(int i = 0; i<lines.length;i++) {
			if(lines[i].contains("Sockets")) {
				String sockets = lines[i].substring("Sockets: ".length());
				for(int j = 0; j<sockets.length();j++) {
					if(sockets.charAt(j)=='R'||sockets.charAt(j)=='G'||sockets.charAt(j)=='B'||sockets.charAt(j)=='W')
						item.sockets++;
					if(sockets.charAt(j)==' ') {
						if(currentLinks>maxLinks) {
							maxLinks=currentLinks;
							currentLinks=1;
						}

					}
					else if(sockets.charAt(j)=='-'){
						currentLinks++;
					}
				}
				item.links=maxLinks;
				return;
			}
		}
	}
	public static int getBaseType(String s) {
		//gloves
		for(int i = 0; i<gloveBases.length;i++) {
			if(s.contains(gloveBases[i])) {
				return GLOVES;
			}
		}
		//boots
		for(int i = 0; i<bootBases.length;i++) {
			if(s.contains(bootBases[i])) {
				return BOOTS;
			}
		}
		//chests
		for(int i = 0; i<chestBases.length;i++) {
			if(s.contains(chestBases[i])) {
				return BODY_ARMOUR;
			}
		}
		//helmets
		for(int i = 0; i<helmetBases.length;i++) {
			if(s.contains(helmetBases[i])) {
				return HELMET;
			}
		}
		//amulets
		for(int i = 0; i<amuletBases.length;i++) {
			if(s.contains(amuletBases[i])) {
				return AMULET;
			}
		}
		//rings
		for(int i = 0; i<ringBases.length;i++) {
			if(s.contains(ringBases[i])) {
				return RING;
			}
		}
		//belts
		for(int i = 0; i<beltBases.length;i++) {
			if(s.contains(beltBases[i])) {
				return BELT;
			}
		}
		//shields
		for(int i = 0; i<shieldBases.length;i++) {
			if(s.contains(shieldBases[i])) {
				return SHIELD;
			}
		}
		//weapon
		for(int i = 0; i<bowBases.length;i++) {
			if(s.contains(bowBases[i])) {
				return BOW;
			}
		}
		//weapon
		for(int i = 0; i<staffBases.length;i++) {
			if(s.contains(staffBases[i])) {
				return STAFF;
			}
		}
		//weapon
		for(int i = 0; i<twoHandSwordBases.length;i++) {
			if(s.contains(twoHandSwordBases[i])) {
				return TWO_HAND_SWORD;
			}
		}
		//weapon
		for(int i = 0; i<twoHandAxeBases.length;i++) {
			if(s.contains(twoHandAxeBases[i])) {
				return TWO_HAND_AXE;
			}
		}
		//weapon
		for(int i = 0; i<twoHandMaceBases.length;i++) {
			if(s.contains(twoHandMaceBases[i])) {
				return TWO_HAND_MACE;
			}
		}
		//weapon
		for(int i = 0; i<clawBases.length;i++) {
			if(s.contains(clawBases[i])) {
				return CLAW;
			}
		}
		//weapon
		for(int i = 0; i<daggerBases.length;i++) {
			if(s.contains(daggerBases[i])) {
				return DAGGER;
			}
		}
		//weapon
		for(int i = 0; i<wandBases.length;i++) {
			if(s.contains(wandBases[i])) {
				return WAND;
			}
		}
		//weapon
		for(int i = 0; i<oneHandSwordBases.length;i++) {
			if(s.contains(oneHandSwordBases[i])) {
				return ONE_HAND_SWORD;
			}
		}
		//weapon
		for(int i = 0; i<thrustingSwordBases.length;i++) {
			if(s.contains(thrustingSwordBases[i])) {
				return THRUSTING_SWORD;
			}
		}
		//weapon
		for(int i = 0; i<oneHandAxeBases.length;i++) {
			if(s.contains(oneHandAxeBases[i])) {
				return ONE_HAND_AXE;
			}
		}
		//weapon
		for(int i = 0; i<oneHandMaceBases.length;i++) {
			if(s.contains(oneHandMaceBases[i])) {
				return ONE_HAND_MACE;
			}
		}
		//weapon
		for(int i = 0; i<sceptreBases.length;i++) {
			if(s.contains(sceptreBases[i])) {
				return SCEPTRE;
			}
		}

		//flasks
		if(s.contains("Flask")) return FLASK;
		if(s.contains("Fishing Rod")) return FISHING_ROD;
		if(s.contains("Quiver")) return QUIVER;
		return -1;
	}
	public static void clearClipboard() {
		StringSelection stringSelection = new StringSelection("");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}
	public static void Draw(Graphics g) {
		item=null;
		Font font = new Font("Iwona Heavy",Font.PLAIN,12);
		g.setFont(font);
		FontMetrics m = g.getFontMetrics();

		//set background color
		g.setColor(new Color(200,200,200));
		g.fillRect(0, 0, 1920, 1080);

		//get data from clipboard
		String data = getClipboardData();

		//if the clipboard has something in it
		if(data!=null) {
			//split the clipboard data into lines
			String lines[] = data.split("\\r?\\n");

			//if the clipboard contains item data
			if(lines[0].contains("Rarity:")) {
				item = new Item();

				if(!data.contains("Unidentified")) item.identified = true;

				//get the rarity of the item
				item.rarity = getRarity(lines[0]);

				//determine the type of item if it's a piece of gear
				if((item.rarity==NORMAL||item.rarity==MAGIC||item.rarity==RARE||item.rarity==UNIQUE)) {
					if(item.identified&&(item.rarity==RARE||item.rarity==UNIQUE)) {
						item.baseType = getBaseType(lines[2]);
					}
					else {
						item.baseType = getBaseType(lines[1]);
					}
				}
				getDPS(lines);
				getSockets(lines);
				for(int i = 0; i<lines.length;i++) {
					g.setColor(Color.BLACK);
					g.drawString(lines[i],5,20+(i*12));
				}

				item.Draw(g);
			}
		}
		else {
			item = null;
		}
	}
}
