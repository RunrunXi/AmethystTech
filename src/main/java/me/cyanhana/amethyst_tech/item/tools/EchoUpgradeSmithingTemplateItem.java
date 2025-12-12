package me.cyanhana.amethyst_tech.item.tools;

import me.cyanhana.amethyst_tech.AmethystTech;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class EchoUpgradeSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    // 应用槽位图标 - 显示可以升级的工具类型
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");

    // 材料槽位图标 - 显示需要的升级材料
    private static final ResourceLocation EMPTY_SLOT_BLOCK = ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, "item/empty_slot_block");

    // 翻译键
    private static final String TRANSLATION_KEY_BASE = Util.makeDescriptionId("item",
            ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, "smithing_template.echo_upgrade"));
    private static final String APPLIES_TO_KEY = TRANSLATION_KEY_BASE + ".applies_to";
    private static final String INGREDIENTS_KEY = TRANSLATION_KEY_BASE + ".ingredients";
    private static final String BASE_SLOT_DESCRIPTION_KEY = TRANSLATION_KEY_BASE + ".base_slot_description";
    private static final String ADDITIONS_SLOT_DESCRIPTION_KEY = TRANSLATION_KEY_BASE + ".additions_slot_description";

    public EchoUpgradeSmithingTemplateItem() {
        super(
            // appliesTo: 描述该模板适用于哪些物品
            Component.translatable(APPLIES_TO_KEY).withStyle(DESCRIPTION_FORMAT),

            // ingredients: 描述需要的材料
            Component.translatable(INGREDIENTS_KEY).withStyle(DESCRIPTION_FORMAT),

            // upgradeDescription: 模板本身的描述
            Component.translatable(Util.makeDescriptionId("upgrade",
                            ResourceLocation.fromNamespaceAndPath(AmethystTech.MODID, "echo_upgrade")))
                    .withStyle(TITLE_FORMAT),

            // baseSlotDescription: 基础物品槽位描述
            Component.translatable(BASE_SLOT_DESCRIPTION_KEY),

            // additionsSlotDescription: 附加材料槽位描述
            Component.translatable(ADDITIONS_SLOT_DESCRIPTION_KEY),

            // baseSlotEmptyIcons: 基础槽位适用的物品类型图标
            List.of(EMPTY_SLOT_SWORD, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL),

            // additionalSlotEmptyIcons: 附加材料槽位图标
            List.of(EMPTY_SLOT_BLOCK)
        );
    }
}
