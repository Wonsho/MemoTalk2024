package com.wons.memotalk.entity;

import com.wons.memotalk.R;

enum IconId {
    DEFAULT_ICON(R.drawable.default_icon),
    ICON_1(R.drawable.account_circle_icon),
    ICON_2(R.drawable.book_icon),
    ICON_3(R.drawable.book_ribbon_icon),
    ICON_4(R.drawable.captive_portal_icon),
    ICON_5(R.drawable.clinical_notes_icon),
    ICON_6(R.drawable.contact_phone_icon),
    ICON_7(R.drawable.credit_score_icon),
    ICON_8(R.drawable.description_icon),
    ICON_9(R.drawable.edit_icon),
    ICON_10(R.drawable.edit_note_icon),
    ICON_11(R.drawable.emoji_transportation_icon),
    ICON_12(R.drawable.globe_asia_icon),
    ICON_13(R.drawable.groups_icon),
    ICON_14(R.drawable.heart_plus_icon),
    ICON_15(R.drawable.home_health_icon),
    ICON_16(R.drawable.home_icon),
    ICON_17(R.drawable.list_alt_icon),
    ICON_18(R.drawable.lock_person_icon),
    ICON_19(R.drawable.lock_reset_icon),
    ICON_20(R.drawable.map_icon),
    ICON_21(R.drawable.medication_liquid_icon),
    ICON_22(R.drawable.monitoring_icon),
    ICON_23(R.drawable.payments_icon),
    ICON_24(R.drawable.pill_icon),
    ICON_25(R.drawable.pin_drop_icon),
    ICON_26(R.drawable.pregnancy_icon),
    ICON_27(R.drawable.query_stats_icon),
    ICON_28(R.drawable.receipt_long_icon),
    ICON_29(R.drawable.shopping_cart_icon),
    ICON_30(R.drawable.strategy_icon),
    ICON_31(R.drawable.thumb_up_icon),
    ICON_32(R.drawable.travel_explore_icon),
    ICON_33(R.drawable.workspace_premium_icon),
    ICON_34(R.drawable.youtube_activity_icon);

    private int id;

    IconId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
