import { MenuItem } from './model';


export const SIDEBAR_ITEMS: MenuItem[] = [
  {
    text: 'pages.home',
    path: 'home',
    icon: 'home',
  },
  {
    text: 'pages.approach.title',
    icon: 'work',
    expanded: false,
    children: [{
      text: 'pages.approach.add', path:'approachManagementCreate'
    },
    {
      text: 'pages.approach.edit', path:'arbitrationManagementCreate'
    },
    {
      text: 'pages.approach.list', path:'arbitrationManagementCreate'
    }
    ]
  },
  {
    text: 'pages.user.title',
    icon: 'person',
    expanded: false,
    children: [{
      text: 'pages.user.add', path:'userManagementCreate' },
    { text: 'pages.user.list', path:'userManagementList' }
    ]
  }

];
