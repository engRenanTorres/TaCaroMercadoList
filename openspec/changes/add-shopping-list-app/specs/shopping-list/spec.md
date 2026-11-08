# Spec Delta

## Purpose

Enables multiple users to create, manage, and share shopping lists across devices with offline-first sync, supporting item operations, filtering, and collaboration.

## ADDED Requirements

### Requirement: User can create and manage multiple shopping lists

The system SHALL allow users to create, name, and manage multiple shopping lists, each representing a separate shopping trip or store visit.

#### Scenario: Create new shopping list
- **WHEN** user taps "New List" button
- **THEN** system displays list creation dialog
- **AND** user enters list name
- **AND** system saves new list with entered name

#### Scenario: Delete shopping list
- **WHEN** user taps delete button on a list
- **THEN** system displays confirmation dialog
- **AND** after confirmation, system removes the list and all its items

#### Scenario: Rename shopping list
- **WHEN** user taps edit button on a list
- **THEN** system displays list name in editable field
- **AND** after saving, system updates the list name

### Requirement: User can add and edit items in a shopping list

The system SHALL allow users to add new items and modify existing item details including name, quantity, price, and category.

#### Scenario: Add new item
- **WHEN** user taps "Add Item" button
- **THEN** system displays item creation form
- **AND** user enters item name
- **AND** user optionally enters quantity and price
- **AND** system saves item to current list

#### Scenario: Edit existing item
- **WHEN** user taps edit button on an item
- **THEN** system displays item details in editable form
- **AND** user modifies item name, quantity, or price
- **AND** system updates the item with new values

#### Scenario: Delete item
- **WHEN** user taps delete button on an item
- **THEN** system displays confirmation dialog
- **AND** after confirmation, system removes the item from the list

### Requirement: User can mark items as purchased

The system SHALL allow users to toggle item purchase status with visual indicators.

#### Scenario: Mark item as purchased
- **WHEN** user taps checkbox on an item
- **THEN** system toggles item's purchased status
- **AND** system displays item with visual distinction (e.g., strikethrough or color change)

#### Scenario: Unmark item as purchased
- **WHEN** user taps checkbox on a previously purchased item
- **THEN** system toggles item's purchased status back to unpurchased
- **AND** system removes visual distinction

### Requirement: User can reorder items within a list

The system SHALL allow users to reorder items using drag-and-drop operations.

#### Scenario: Reorder items by dragging
- **WHEN** user drags an item to a new position
- **THEN** system reorders the item in the list
- **AND** system saves the new order

#### Scenario: Reorder items by moving
- **WHEN** user taps move up/down buttons on an item
- **THEN** system moves the item up or down in the list
- **AND** system saves the new order

### Requirement: User can filter and sort shopping list items

The system SHALL provide filtering and sorting options to help users find and organize items.

#### Scenario: Filter by purchased status
- **WHEN** user selects filter option "Show only to buy"
- **THEN** system displays only items marked as unpurchased
- **AND** items marked as purchased are hidden

#### Scenario: Sort by name
- **WHEN** user selects sort option "Sort by Name"
- **THEN** system displays items alphabetically by name

#### Scenario: Sort by price
- **WHEN** user selects sort option "Sort by Price"
- **THEN** system displays items sorted by price (lowest to highest)

#### Scenario: Sort by category
- **WHEN** user selects sort option "Sort by Category"
- **THEN** system displays items grouped by category

#### Scenario: Search items
- **WHEN** user enters search query in search field
- **THEN** system displays only items matching the query
- **AND** search is case-insensitive

### Requirement: User can add price to items

The system SHALL allow users to add and edit prices for individual items, with automatic calculation of total price.

#### Scenario: Add price to item
- **WHEN** user enters price for an item
- **THEN** system displays the price next to the item
- **AND** system calculates and displays total price for the list

#### Scenario: Edit item price
- **WHEN** user modifies price for an item
- **THEN** system updates the displayed price
- **AND** system recalculates the total price for the list

#### Scenario: Calculate total price
- **WHEN** user adds or modifies item prices
- **THEN** system calculates the sum of all item prices
- **AND** system displays the total price

### Requirement: User can add quantity to items

The system SHALL allow users to add and edit quantities for items, with automatic calculation of total price based on quantity.

#### Scenario: Add quantity to item
- **WHEN** user enters quantity for an item
- **THEN** system displays the quantity next to the item
- **AND** system calculates and displays total price (price × quantity)

#### Scenario: Edit item quantity
- **WHEN** user modifies quantity for an item
- **THEN** system updates the displayed quantity
- **AND** system recalculates the total price for the list

#### Scenario: Calculate total price with quantity
- **WHEN** user adds or modifies item quantity
- **THEN** system calculates the sum of (price × quantity) for all items
- **AND** system displays the total price

### Requirement: User can toggle edit mode

The system SHALL allow users to enable or disable edit mode, controlling which actions are available.

#### Scenario: Enable edit mode
- **WHEN** user toggles edit mode switch to ON
- **THEN** system enables add, edit, and delete actions for items
- **AND** system displays edit mode indicator

#### Scenario: Disable edit mode
- **WHEN** user toggles edit mode switch to OFF
- **THEN** system disables add, edit, and delete actions for items
- **AND** system hides edit mode indicator

### Requirement: User can sync shopping list with nearby devices

The system SHALL allow users to sync shopping lists with other devices in close proximity, supporting both online and offline scenarios.

#### Scenario: Discover nearby devices
- **WHEN** user enables sync feature
- **THEN** system scans for nearby devices
- **AND** system displays discovered devices

#### Scenario: Sync list with nearby device
- **WHEN** user selects a nearby device
- **THEN** system initiates sync with selected device
- **AND** system transfers shopping list data to the device

#### Scenario: Sync with internet
- **WHEN** user enables internet sync
- **THEN** system syncs shopping list with cloud service
- **AND** system synchronizes changes between devices

#### Scenario: Offline sync
- **WHEN** user enables offline sync
- **THEN** system stores shopping list locally
- **AND** system syncs when internet connection is restored

### Requirement: Multiple users can collaborate on shopping lists

The system SHALL support multiple users sharing the same shopping list with synchronized data.

#### Scenario: Add user to list
- **WHEN** user invites another user to a shopping list
- **THEN** system sends invitation to the user
- **AND** invited user can access and modify the list

#### Scenario: View other users' changes
- **WHEN** another user modifies the shared list
- **THEN** system displays the changes in real-time
- **AND** system shows which user made each change

#### Scenario: Conflict resolution
- **WHEN** two users modify the same item simultaneously
- **THEN** system resolves conflicts based on timestamp or user preference
- **AND** system notifies users of conflicts

### Requirement: System operates offline-first

The system SHALL allow full functionality without internet connectivity, with synchronization when connection is restored.

#### Scenario: Full offline operation
- **WHEN** user has no internet connection
- **THEN** system allows all shopping list operations
- **AND** system saves all changes locally
- **AND** system syncs when connection is restored

#### Scenario: Offline changes sync on reconnect
- **WHEN** internet connection is restored
- **THEN** system syncs all local changes to cloud
- **AND** system downloads changes from other devices
- **AND** system merges all changes
