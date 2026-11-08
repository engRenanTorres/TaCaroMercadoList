# Supermarket Shopping List App - Exploration Notes

## Core Requirements

1. **Sync with nearby devices** (with or without internet)
2. **Item operations**: read, check off (mark as bought), add price for that day
3. **Edit mode**: add/edit items only when enabled

---

## Questions to Clarify

### Sync Strategy

**"Nearby devices with or without internet"** - What does "nearby" mean to you?

- [ ] Bluetooth Low Energy (BLE) - good for short range, no network needed
- [ ] Wi-Fi Direct / Local Network - requires devices on same Wi-Fi
- [ ] NFC - very short range, requires physical contact
- [ ] Peer-to-peer mesh network - more complex, but can cover larger areas
- [ ] A combination (e.g., BLE for discovery, Wi-Fi for data transfer)
- [x] Combination of the easiest implementation and with no costs

**"With or without internet"** - This suggests an offline-first approach. Does that mean:
- [ ] Full offline sync (list works completely offline, syncs when connection returns)
- [x] Hybrid (some features require internet, others work offline)
- [ ] Offline-first with optional cloud backup

---

### Item Operations

**"Add price in that day"** - I'm hearing two possibilities:
- [ ] Price per item (e.g., "Milk: $3.50")
- [ ] Price for the shopping trip (e.g., "Today's total: $45.20")
- [ ] Both
- [x] Price per item editable and Today's total sum all item's price
- [x] Also should have editable quantity that multiple the item price in that day

**"Check after we buy"** - How should this work?
- [x] Simple checkbox that toggles state
- [ ] Separate "bought" vs "to buy" lists
- [ ] Visual strikethrough on the main list
- [x] Mark as "purchased" vs "not purchased" with different colors

---

### Edit Mode

**"Add or edit items will be able only in a edit mode"** - How do you envision this?
- [x] A toggle switch at the top of the screen
- [ ] A password/pattern to unlock editing
- [ ] Time-based (e.g., only during certain hours)
- [ ] A specific "Edit" button that must be tapped

---

### Reading Items

**"In the list you will be able to read the items"** - Just viewing, or do you need:
- [x] Filtering (e.g., show only items marked "to buy")
- [x] Sorting (by name, price, category)
- [x] Categorization (produce, dairy, etc.)
- [x] Search functionality

---

### Additional Questions

- **Who are the users?** Just you, or multiple people sharing the list? multiple people.
- **What's the primary use case?** Quick shopping trip, meal planning, tracking prices over time? Monthly shooping with my wife. 
- **What's the priority?** Offline sync, ease of use, feature completeness, or something else?
Offline sync.

---

## Notes / Additional Thoughts

- I also would like to change the position of the item in list. And save the order of lists with different names. Because it will be the order that I will look for the items in different supermarkets. 

*(Add your answers and any other thoughts here)*
