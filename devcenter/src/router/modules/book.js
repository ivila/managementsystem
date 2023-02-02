import Layout from '@/components/system/layout.vue'
import BookList from '@/components/book/list.vue'

const routes = {
  path: '/book/managemant',
  component: Layout,
  meta: { title: 'Book Managemant' },
  children: [
    {
      path: 'list',
      component: BookList,
      meta: { title: 'Book Managemant' }
    }
  ]
}
export default routes
