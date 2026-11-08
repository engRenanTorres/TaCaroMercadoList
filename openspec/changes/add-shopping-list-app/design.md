# Design

## Context

This is a greenfield Android application for a shopping list that needs to support multiple users (you and your wife) with offline-first operation and peer-to-peer sync. The primary use case is monthly shopping trips where users need to collaborate on a shared list, track purchases, and manage items across different supermarkets.

## Goals / Non-Goals

**Goals:**
- Enable multiple users to collaborate on shopping lists
- Support offline-first operation with local storage
- Provide peer-to-peer sync for nearby devices
- Implement comprehensive item management (add, edit, reorder, check off, price, quantity)
- Support filtering, sorting, categorization, and search
- Enable multiple saved lists for different stores

**Non-Goals:**
- Cloud backup service (focus on local sync)
- Price history tracking over time
- Integration with external inventory systems
- Advanced analytics or statistics
- Voice input for item names

## Decisions

### Technology Stack

**Android Development:**
- **Language**: Kotlin (modern, idiomatic Android development)
- **UI Framework**: Jetpack Compose (declarative UI, easier state management)
- **Architecture**: MVVM with Clean Architecture principles
- **Navigation**: Jetpack Navigation Compose

**Data Storage:**
- **Local Database**: Room (SQLite abstraction, type-safe queries, compile-time checks)
- **Data Model**: Room entities with relationships for lists, items, and users

**Networking:**
- **Peer-to-Peer Sync**: Nearby Connections API (Google Play Services) for device discovery and data transfer
- **Offline-First**: Room database as primary storage, sync when connection available
- **Optional Cloud Sync**: Firebase Firestore for optional cloud backup (future enhancement)

**State Management:**
- **ViewModel**: Jetpack ViewModel for UI state preservation
- **StateFlow**: Reactive state management for list data
- **Room Database**: Asynchronous database operations with Flow

**UI Components:**
- **Compose UI**: Modern declarative UI
- **Material Design 3**: Google's latest design system
- **Drag and Drop**: Compose drag-and-drop API for reordering items

### Sync Strategy

**Primary Sync: Nearby Connections API**
- Uses Bluetooth LE for device discovery (works without internet)
- Transfers shopping list data via Bluetooth
- Supports multiple devices in close proximity
- No internet required for basic sync

**Secondary Sync: Optional Cloud Sync**
- Firebase Firestore for optional cloud backup
- Syncs when internet is available
- Provides backup if devices are not in proximity

**Rationale:**
- Nearby Connections API is free and requires no additional infrastructure
- Bluetooth works in offline scenarios (supermarkets often have poor WiFi)
- Supports multiple users on the same list
- Simple implementation with good performance for small data (shopping list)

**Alternatives Considered:**
- Wi-Fi Direct: Requires devices on same Wi-Fi network, less reliable in public spaces
- Bluetooth Classic: Higher power consumption, slower data transfer
- Custom P2P protocol: More complex, requires more development effort
- Cloud-only sync: Requires internet, doesn't meet offline-first requirement

### Data Model

**Entities:**
- `ShoppingList`: Represents a shopping list with name, creation date, and user ID
- `ShoppingListItem`: Represents an item with name, quantity, price, category, purchased status, and position
- `User`: Represents a user with ID, name, and avatar
- `SyncState`: Tracks sync status and timestamps

**Relationships:**
- One ShoppingList has many ShoppingListItems
- Multiple users can access the same ShoppingList
- ShoppingListItem belongs to one ShoppingList

**Rationale:**
- Simple, normalized structure
- Room's relationship support simplifies queries
- Easy to extend (e.g., add categories, tags)

**Alternatives Considered:**
- Single table for all data: Simpler but harder to maintain, less scalable
- No relationships: More complex queries, harder to maintain

### Edit Mode Implementation

**Toggle Switch at Top of Screen**
- Simple on/off switch visible to all users
- When off: Only view operations (read, filter, sort, search)
- When on: All operations (add, edit, delete, reorder)

**Rationale:**
- Simple, intuitive for users
- Clear visual indicator of edit mode status
- Easy to implement with Compose

**Alternatives Considered:**
- Password/pattern protection: Adds friction, not necessary for personal use
- Time-based editing: Less flexible, not needed for this use case

### Item Operations

**Check Off:**
- Simple checkbox with visual feedback (strikethrough or color change)
- Toggles purchased/not purchased status
- Can be undone

**Price and Quantity:**
- Price per item (editable)
- Quantity per item (editable)
- Total price calculated as sum(price × quantity)
- Real-time updates when values change

**Reordering:**
- Drag-and-drop using Compose drag-and-drop API
- Alternative: Up/down buttons for accessibility
- Position saved with each item

**Rationale:**
- Simple, intuitive interface
- Real-time calculations provide immediate feedback
- Drag-and-drop is modern and user-friendly

**Alternatives Considered:**
- Separate "bought" and "to buy" lists: More complex UI, users may forget to move items
- Visual strikethrough only: Doesn't clearly indicate purchased status

### Filtering and Sorting

**Filter Options:**
- Show only to buy (hide purchased items)
- Show all items
- Show only purchased items

**Sort Options:**
- Sort by name (alphabetical)
- Sort by price (lowest to highest)
- Sort by category (grouped)
- Sort by position (original order)

**Search:**
- Real-time search as user types
- Case-insensitive matching
- Searches item names

**Rationale:**
- Covers common use cases
- Simple to implement with Room queries
- Provides flexibility for different shopping scenarios

**Alternatives Considered:**
- Advanced filters (date, category, price range): Overkill for this use case
- Custom filter combinations: More complex UI, less intuitive

### Multiple Lists

**List Management:**
- Create new lists with custom names
- Rename existing lists
- Delete lists with confirmation
- Each list has its own items and order

**Rationale:**
- Supports different shopping scenarios (different stores, different trips)
- Simple implementation with Room relationships
- Easy to understand and use

**Alternatives Considered:**
- Single list with categories: Less flexible, doesn't support different stores
- List templates: More complex, not needed for this use case

## Risks / Trade-offs

**Risk: Bluetooth Range Limitation**
- Nearby Connections API has limited range (typically 10-30 meters)
- Users may not be in range during shopping trips
- **Mitigation**: Optional cloud sync for backup, clear UI indicators for sync status

**Risk: Multiple Users Conflict**
- Two users editing the same item simultaneously may cause conflicts
- **Mitigation**: Timestamp-based conflict resolution, user notifications for conflicts

**Risk: Data Loss on Device Failure**
- Local database may be corrupted or lost
- **Mitigation**: Regular sync with cloud (optional), user education on backup

**Trade-off: Offline-First vs Real-Time**
- Offline-first means changes may not sync immediately
- **Mitigation**: Clear sync indicators, manual sync options, background sync when connection restored

**Trade-off: Complexity vs Features**
- Rich feature set requires more development time
- **Mitigation**: Prioritize core features, implement advanced features in future iterations

## Migration Plan

**Deployment:**
1. Release as Android app on Google Play Store
2. Provide in-app instructions for setup
3. Guide users through initial sync setup

**Rollback:**
- No complex migration needed (greenfield app)
- If critical issues arise, can be removed from Play Store
- Data stored locally, no cloud dependencies

**User Migration:**
- New users start fresh with the app
- Existing users (if any) would need to export/import data (future enhancement)
