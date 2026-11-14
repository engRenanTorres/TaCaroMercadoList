# Tasks

## 1. Project Setup

- [x] 1.1 Create new Android project with Kotlin and Jetpack Compose
- [x] 1.2 Add required dependencies (Room, Navigation Compose, Nearby Connections, StateFlow)
- [x] 1.3 Set up project structure (modules, package organization)
- [x] 1.4 Configure build.gradle files for dependencies and signing
- [x] 1.5 Verify project builds and runs on target Android devices

## 2. Data Layer Setup

- [ ] 2.1 Create Room database configuration and DAO interfaces
- [ ] 2.2 Define Room entities (ShoppingList, ShoppingListItem, User, SyncState)
- [ ] 2.3 Implement database initialization and migration strategy
- [ ] 2.4 Create repository classes for data access
- [ ] 2.5 Implement Flow-based data streams for reactive updates
- [ ] 2.6 Verify database operations (CRUD) work correctly

## 3. User Management

- [ ] 3.1 Implement user creation and authentication flow
- [ ] 3.2 Create User entity and database operations
- [ ] 3.3 Implement user profile UI (name, avatar)
- [ ] 3.4 Add user selection for shared lists
- [ ] 3.5 Verify multiple users can access the same list

## 4. Shopping List Management

- [ ] 4.1 Implement ShoppingList entity and database operations
- [ ] 4.2 Create ShoppingListViewModel with state management
- [ ] 4.3 Implement list creation UI (name input, save)
- [ ] 4.4 Implement list selection and display
- [ ] 4.5 Implement list renaming and deletion
- [ ] 4.6 Verify list operations persist correctly

## 5. Shopping List Item Management

- [ ] 5.1 Implement ShoppingListItem entity and database operations
- [ ] 5.2 Create item creation UI (name, quantity, price, category)
- [ ] 5.3 Implement item editing UI
- [ ] 5.4 Implement item deletion with confirmation
- [ ] 5.5 Verify item CRUD operations work correctly

## 6. Item Operations (Check Off)

- [ ] 6.1 Implement purchased status toggle in UI
- [ ] 6.2 Add visual indicators for purchased items (strikethrough, color change)
- [ ] 6.3 Implement undo functionality for purchased items
- [ ] 6.4 Verify purchased status persists correctly

## 7. Item Reordering

- [ ] 7.1 Implement drag-and-drop API for item reordering
- [ ] 7.2 Implement alternative up/down buttons for accessibility
- [ ] 7.3 Save item position in database
- [ ] 7.4 Verify reordering persists and displays correctly

## 8. Price and Quantity Management

- [ ] 8.1 Implement price input field for items
- [ ] 8.2 Implement quantity input field for items
- [ ] 8.3 Implement real-time total price calculation
- [ ] 8.4 Verify price and quantity updates trigger recalculation
- [ ] 8.5 Test edge cases (zero price, zero quantity, negative values)

## 9. Filtering and Sorting

- [ ] 9.1 Implement filter UI (Show only to buy, Show all, Show purchased)
- [ ] 9.2 Implement sort UI (Name, Price, Category, Position)
- [ ] 9.3 Implement search field with real-time filtering
- [ ] 9.4 Verify filter and sort operations work correctly
- [ ] 9.5 Test search with various inputs (case-insensitive)

## 10. Edit Mode

- [ ] 10.1 Implement edit mode toggle switch UI
- [ ] 10.2 Add edit mode indicator in UI
- [ ] 10.3 Disable add, edit, delete, and reorder actions when edit mode is off
- [ ] 10.4 Enable all actions when edit mode is on
- [ ] 10.5 Verify edit mode behavior matches requirements

## 11. Offline-First Operation

- [ ] 11.1 Implement local Room database as primary storage
- [ ] 11.2 Verify all operations work without internet
- [ ] 11.3 Implement background sync when connection is restored
- [ ] 11.4 Add sync status indicators in UI
- [ ] 11.5 Verify offline changes sync correctly on reconnect

## 12. Peer-to-Peer Sync (Nearby Connections)

- [ ] 12.1 Implement Nearby Connections API setup and initialization
- [ ] 12.2 Implement device discovery and connection UI
- [ ] 12.3 Implement shopping list data transfer between devices
- [ ] 12.4 Handle connection lifecycle (connect, disconnect, errors)
- [ ] 12.5 Verify sync works with multiple nearby devices
- [ ] 12.6 Test sync with different device configurations

## 13. Optional Cloud Sync (Firebase Firestore)

- [ ] 13.1 Set up Firebase project and Firestore database
- [ ] 13.2 Implement cloud sync when internet is available
- [ ] 13.3 Implement cloud backup functionality
- [ ] 13.4 Verify cloud sync works correctly
- [ ] 13.5 Test conflict resolution for cloud sync

## 14. UI/UX Implementation

- [ ] 14.1 Design and implement main screen layout
- [ ] 14.2 Implement navigation between screens
- [ ] 14.3 Add loading and error states
- [ ] 14.4 Implement empty states for lists and items
- [ ] 14.5 Add Material Design 3 components and styling
- [ ] 14.6 Verify responsive design for different screen sizes

## 15. Testing

- [ ] 15.1 Write unit tests for Room database operations
- [ ] 15.2 Write unit tests for ViewModel logic
- [ ] 15.3 Write unit tests for data transformation and calculation
- [ ] 15.4 Write UI tests for critical user flows
- [ ] 15.5 Verify all tests pass

## 16. Documentation

- [ ] 16.1 Add code comments for complex logic
- [ ] 16.2 Create user guide for app features
- [ ] 16.3 Document setup and installation process
- [ ] 16.4 Verify documentation is clear and accurate

## 17. Build and Release

- [ ] 17.1 Configure app signing for Play Store release
- [ ] 17.2 Run full build and verify no errors
- [ ] 17.3 Test on multiple Android device configurations
- [ ] 17.4 Prepare release notes
- [ ] 17.5 Submit app to Google Play Store

## Workflow follow-up

- Archive the change after the project's review requirements are satisfied.
- Verify the archived result.
